package com.itshizhan.handlerdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class HandlerOne extends AppCompatActivity {
    private ProgressBar mProgressBar;
    private Button mButtonStart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_one);
        mButtonStart = findViewById(R.id.start);
        mProgressBar = findViewById(R.id.progress_bar);
        mButtonStart.setOnClickListener(new StartOnClickListener());

        //打印activtiy线程信息
        System.out.println("activity_id---->"+Thread.currentThread().getId());
        System.out.println("activity_name---->"+Thread.currentThread().getName());
    }


    //创建一个handler，内部完成处理消息方法
    Handler update_progress_bar = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            //super.handleMessage(msg);
            //显示进度条
            mProgressBar.setProgress(msg.arg1);
            //重新把进程加入到进程队列中
            update_progress_bar.post(update_thread);
        }
    };

    Runnable update_thread = new Runnable()
    {
        int i = 0;
        public void run() {
            System.out.println("handler_id---->"+Thread.currentThread().getId());
            System.out.println("handler_name---->"+Thread.currentThread().getName());
            // TODO Auto-generated method stub
            i += 10;
            //首先获得一个消息结构
            Message msg = update_progress_bar.obtainMessage();
            //给消息结构的arg1参数赋值
            msg.arg1 = i;
            //延时1s，java中的try+catch用来排错处理
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
            //把消息发送到消息队列中
            update_progress_bar.sendMessage(msg);
            if(i == 100){
                //把线程从线程队列中移除
                update_progress_bar.removeCallbacks(update_thread);
                //隐藏进度条
                //mProgressBar.setVisibility(View.GONE);
            }

        }
    };

    private class StartOnClickListener implements View.OnClickListener {


        @Override
        public void onClick(View v) {
            //让进度条显示出来
            mProgressBar.setVisibility(View.VISIBLE);
            //将线程加入到handler的线程队列中
            update_progress_bar.post(update_thread);
        }
    }
}
