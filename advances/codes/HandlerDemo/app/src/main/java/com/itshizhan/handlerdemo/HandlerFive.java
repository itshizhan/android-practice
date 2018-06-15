package com.itshizhan.handlerdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HandlerFive extends AppCompatActivity {

    private Handler mHandler;
    private TextView mTextView;
    private Button mButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five);
        mTextView = (TextView) findViewById(R.id.textView);
        mButton = (Button)findViewById(R.id.button);

        //  此处需要优化，存在内存泄漏
        mHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                //super.handleMessage(msg);
                mTextView.setText("" + msg.what);

            }
        };

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 1; i <= 10; i++) {
                    Message message = Message.obtain(mHandler);
                    message.what = 10 - i;
                    //通过延迟发送消息，每隔一秒发送一条消息
                    mHandler.sendMessageDelayed(message, 1000 * i);
                }
            }
        });
    }

}
