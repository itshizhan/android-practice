package com.itshizhan.handlerdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HandlerSix extends AppCompatActivity {

    //private Handler mHandler;
    private TextView mTextView;
    private Button mButton;

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five);

        mTextView = (TextView) findViewById(R.id.textView);
        mButton = (Button)findViewById(R.id.button);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 1; i <= 10; i++) {
                    final int fadedSecond = i;
                    //每延迟一秒，发送一个Runnable对象
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mTextView.setText(""+ (10-fadedSecond));
                        }
                    },1000*i);
                }

            }
        });
    }
}
