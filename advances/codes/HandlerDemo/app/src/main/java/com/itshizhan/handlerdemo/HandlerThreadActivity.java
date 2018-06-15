package com.itshizhan.handlerdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class HandlerThreadActivity extends AppCompatActivity {

    private TextView tv_A, tv_B;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handlerthread);

        tv_A = (TextView) findViewById(R.id.tv_a);
        tv_B = (TextView) findViewById(R.id.tv_b);


        final Handler mainHandler = new Handler();

        final HandlerThread downloadAThread = new HandlerThread("downloadAThread");
        downloadAThread.start();
        Handler downloadAHandler = new Handler(downloadAThread.getLooper());

        // 通过postDelayed模拟耗时操作
        downloadAHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),
                        "下载A完成", Toast.LENGTH_SHORT).show();
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        tv_A.setText("A任务已经下载完成");
                    }
                });
            }
        }, 1000 * 15);


        final HandlerThread downloadBThread = new HandlerThread("downloadBThread");
        downloadBThread.start();
        Handler downloadBHandler = new Handler(downloadBThread.getLooper());

        // 通过postDelayed模拟耗时操作
        downloadBHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),
                        "下载B完成", Toast.LENGTH_SHORT).show();
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        tv_B.setText("B任务已经下载完成");
                    }
                });

            }
        }, 1000 * 10);

    }

}
