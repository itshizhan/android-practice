package com.itshizhan.handlerdemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class HandlerCountDownActivity extends AppCompatActivity {
    public static final int UPDATE = 0x1;
    TextView mTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);
        mTextView = (TextView) findViewById(R.id.tv_countdown);
        begin();//开启倒计时并跳转页面的方法
    }

    //Handler静态内部类
    private static class MyHandler extends Handler {
        //弱引用
        WeakReference<HandlerCountDownActivity> weakReference;
        public MyHandler(HandlerCountDownActivity activity) {
            weakReference = new WeakReference<HandlerCountDownActivity>(activity);
        }
        @Override
        public void handleMessage(Message msg) {
            HandlerCountDownActivity activity = weakReference.get();
            if (activity != null) {
                activity.mTextView.setText(String.valueOf(msg.arg1));
            }
        }
    }
    private MyHandler handler = new MyHandler(this);

    private void begin() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 5; i > 0; i--) {
                    Message msg = new Message();
                    msg.what = UPDATE;
                    msg.arg1 = i;
                    handler.sendMessage(msg);
                    try {
                        Thread.sleep(1000);//休眠1秒
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.i("tag", HandlerCountDownActivity.this + "-" + i);
                }
                //计时结束后跳转到其他界面
                startActivity(new Intent(HandlerCountDownActivity.this, HandlerFive.class));
                //添加finish方法在任务栈中销毁倒计时界面，使新开界面在回退时直接退出而不是再次返回该界面
                finish();
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
        Log.i("tag", "destory");
    }
}
