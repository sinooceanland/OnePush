package com.peng.one.push1;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.peng.one.push.OnePush;
import com.peng.one.push1.R;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RequestExecutor;
import com.yanzhenjie.permission.runtime.Permission;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private static final String PUSH_DATA = "PUSH_DATA";
    private static final String ACTION_LOG = "com.peng.one.push.ACTION_LOG";

    private TextView tvLog;


    private int[] resId = new int[]{
        R.id.btn_register_push, R.id.btn_unregister_push, R.id.btn_set_tag,
            R.id.btn_unset_tag, R.id.btn_bind_alias, R.id.btn_unbind_alias};

    private BroadcastReceiver mLogReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            initCurrentPushData(intent);
        }
    };

    private IntentFilter mLogFilter = new IntentFilter(ACTION_LOG);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTitle();
        initView();
        initEvent();
        initCurrentPushData(getIntent());
    }

    private void requestAllPermission(){
        AndPermission.with(this)
                .runtime()
                .permission(Permission.Group.STORAGE,Permission.Group.LOCATION)
                .rationale(new Rationale<List<String>>() {
                    @Override
                    public void showRationale(Context context, List<String> data, RequestExecutor executor) {
                        new AlertDialog.Builder(context)
                                .setMessage("请同意app权限，否则app，将不能使用")
                                .setPositiveButton("继续", (dialog, button) -> executor.execute())
                                .setNegativeButton("取消", (dialog, button) -> executor.cancel())
                                .show();
                    }
                })
                .onGranted(permissions -> {
                    // Storage permission are allowed.
                    OnePush.register();
                })
                .onDenied(permissions -> {
                    // Storage permission are not allowed.
                    if (AndPermission.hasAlwaysDeniedPermission(MainActivity.this, permissions)) {
                        Toast.makeText(this, "拒绝，需要到系统设置，自己设定，否者有可能导致消息推送不成功！", Toast.LENGTH_SHORT).show();
                    }
                })
                .start();
    }

    @Override
    protected void onStart() {
        super.onStart();
        requestAllPermission();
    }

    private void initTitle() {
        getSupportActionBar().setTitle("Push:" + OnePush.getPushPlatFormName() + "--Rom:" + Build.MANUFACTURER );
    }

    private void initView() {
        tvLog = (TextView) findViewById(R.id.log);
    }

    private void initEvent() {
        for (int i : resId) {
            findViewById(i).setOnClickListener(this);
        }
        this.mLogFilter.addCategory(getPackageName());
        registerReceiver(mLogReceiver, mLogFilter);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        initCurrentPushData(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register_push:
                OnePush.register();
                break;
            case R.id.btn_unregister_push:
                OnePush.unRegister();
                break;
            case R.id.btn_set_tag:
                OnePush.addTag("test");
                OnePush.addTag("kkkk");
                OnePush.addTag("1233");
                OnePush.addTag("1111");
                OnePush.addTag("2222");
                break;
            case R.id.btn_unset_tag:
                OnePush.deleteTag("test");
                break;
            case R.id.btn_bind_alias:
                OnePush.bindAlias("test");
                break;
            case R.id.btn_unbind_alias:
                OnePush.unBindAlias("test");
                break;
        }
    }

    private void initCurrentPushData(Intent intent) {
        String stringExtra = intent.getStringExtra(PUSH_DATA);
        if (!TextUtils.isEmpty(stringExtra)) {
            tvLog.append(stringExtra);
            tvLog.append(System.getProperty("line.separator"));
            tvLog.append(System.getProperty("line.separator"));
        }
    }

    /**
     * 启动这个Activity
     * @param context
     * @param pushData
     */
    public static void start(Context context, String pushData) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(PUSH_DATA, pushData);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP);
        }
        context.startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public static void sendLogBroadcast(Context context, String log) {
        Intent intent = new Intent(ACTION_LOG);
        intent.putExtra(PUSH_DATA, log);
        intent.addCategory(context.getPackageName());
        context.sendBroadcast(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mLogReceiver);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        tvLog.setText("");
        return false;
    }
}
