package com.itshizhan.handlerdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class HandlerFour extends AppCompatActivity {
    private TextView mTextView;
    private Handler mHandler;
    /*
    //此方式会报：This Handler class should be static or leaks might occur 警告

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //super.handleMessage(msg);
            mTextView.setText(""+msg.arg1+"--"+msg.obj);
        }

    };
    */

    static class MyHandler extends Handler{
        WeakReference<HandlerFour> mActivity;

        private MyHandler(HandlerFour activity) {
            mActivity = new WeakReference<HandlerFour>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            HandlerFour fourAcitvity = mActivity.get();
            fourAcitvity.mTextView.setText(""+msg.arg1+"="+msg.obj);

        }
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);
        mTextView = findViewById(R.id.text_view);
        mHandler = new MyHandler(this);

        // 开启线程
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    Message msg = new Message();
                    msg.arg1 = 88;
                    msg.obj = "hello";

                    mHandler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
}
