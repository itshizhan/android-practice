package com.itshizhan.broadcastbestpractice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

public class BaseActivity extends AppCompatActivity {

   private ForceOfflineReceiver forceOfflineReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    class ForceOfflineReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(final Context context, Intent intent) {
            // 强制下线对话框
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("警告");
            builder.setMessage("您已被强制下线了，请重新登录");
            builder.setCancelable(false);
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // 销毁所有活动
                    ActivityCollector.finishAll();
                    Intent intent = new Intent(context,LoginActivity.class);
                    //这是在广播接收器中启动活动的，所以要给Intent加入这个标志
                    //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);


                }
            });
            builder.show();
            //AlertDialog alertDialog = builder.create();
            // 设置dialog的类型，确保可以在广播中正常弹窗
            //alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
            //alertDialog.show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.itshizhan.broadcastbestpractice.FORCE_OFFLINE");
        forceOfflineReceiver = new ForceOfflineReceiver();
        registerReceiver(forceOfflineReceiver,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(forceOfflineReceiver!=null){
            unregisterReceiver(forceOfflineReceiver);
            forceOfflineReceiver = null;
        }
    }
}
