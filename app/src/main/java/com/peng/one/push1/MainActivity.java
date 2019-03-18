package com.peng.one.push1;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Process;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.peng.one.push.OnePush;
import com.peng.one.push.entity.OnePushMsg;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RequestExecutor;
import com.yanzhenjie.permission.runtime.Permission;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private static final String PUSH_DATA = "PUSH_DATA";
    private static final String ACTION_LOG = "com.peng.one.push.ACTION_LOG";
    private TextView tvLog;
    private int[] resId = new int[]{
            R.id.btn_register_push, R.id.btn_unregister_push,};
    private BroadcastReceiver mLogReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            initCurrentPushData(intent);
        }
    };
    private IntentFilter mLogFilter = new IntentFilter(ACTION_LOG);

    /**
     * 启动这个Activity
     *
     * @param context
     * @param pushData
     */
    public static void start(Context context, String pushData) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(PUSH_DATA, pushData);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        }
        context.startActivity(intent);
    }

    public static void sendLogBroadcast(Context context, String log) {
        Intent intent = new Intent(ACTION_LOG);
        intent.putExtra(PUSH_DATA, log);
        intent.addCategory(context.getPackageName());
        context.sendBroadcast(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        File file=new File(Environment.getExternalStorageDirectory()+"/test.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init(){
        initTitle();
        initView();
        initEvent();
        initCurrentPushData(getIntent());
    }

    private void requestAllPermission() {
        AndPermission.with(this)
                .runtime()
                .permission(Permission.Group.STORAGE, Permission.Group.LOCATION)
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
        getSupportActionBar().setTitle("Push:" + OnePush.getPushPlatFormName() + "--Rom:" + Build.MANUFACTURER);
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
//            case R.id.btn_set_tag:
//                OnePush.addTag("test");
//                OnePush.addTag("kkkk");
//                OnePush.addTag("1233");
//                OnePush.addTag("1111");
//                OnePush.addTag("2222");
//                break;
//            case R.id.btn_unset_tag:
//                OnePush.deleteTag("test");
//                break;
//            case R.id.btn_bind_alias:
//                OnePush.bindAlias("test");
//                break;
//            case R.id.btn_unbind_alias:
//                OnePush.unBindAlias("test");
//                break;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mLogReceiver);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.switch_channel) {
            showListDialog();
        } else {
            tvLog.setText("");
        }
        return false;
    }

    private void showListDialog() {
        final String[] items = {"极光", "小米", "华为"};
        AlertDialog.Builder listDialog =
                new AlertDialog.Builder(MainActivity.this);
        listDialog.setTitle("切换推送通道");
        listDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    String currentProcessName = getCurrentProcessName();
                    if (BuildConfig.APPLICATION_ID.equals(currentProcessName) || BuildConfig.APPLICATION_ID.concat(":channel").equals(currentProcessName)) {
                        OnePush.init(getApplication(), ((platformCode, platformName) -> platformCode == 106));
                    }
                } else if (which == 1) {
                    String currentProcessName = getCurrentProcessName();
                    if (BuildConfig.APPLICATION_ID.equals(currentProcessName) || BuildConfig.APPLICATION_ID.concat(":channel").equals(currentProcessName)) {
                        OnePush.init(getApplication(), ((platformCode, platformName) -> platformCode == 101));
                    }
                } else if (which == 2) {
                    String currentProcessName = getCurrentProcessName();
                    if (BuildConfig.APPLICATION_ID.equals(currentProcessName) || BuildConfig.APPLICATION_ID.concat(":channel").equals(currentProcessName)) {
                        OnePush.init(getApplication(), ((platformCode, platformName) -> platformCode == 108));
                    }
                }
                tvLog.setText("");
                init();
            }
        });
        listDialog.show();
    }

    /**
     * 获取当前进程名称
     *
     * @return processName
     */
    public String getCurrentProcessName() {
        int currentProcessId = Process.myPid();
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo runningAppProcess : runningAppProcesses) {
            if (runningAppProcess.pid == currentProcessId) {
                return runningAppProcess.processName;
            }
        }
        return null;
    }
}
