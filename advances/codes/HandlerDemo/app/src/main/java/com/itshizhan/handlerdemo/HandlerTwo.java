package com.itshizhan.handlerdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class HandlerTwo extends AppCompatActivity {
    private TextView mTextView;


    Handler mHandler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_two);
        mTextView = findViewById(R.id.text_view);

        //尝试在非UI线程更新UI
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                   // mTextView.setText(R.string.update_view_text);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mTextView.setText(R.string.update_view_text);
                        }
                    });

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }
}
