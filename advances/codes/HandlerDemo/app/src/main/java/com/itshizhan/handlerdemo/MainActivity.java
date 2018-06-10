package com.itshizhan.handlerdemo;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView text_view;
    private Button start;
    private Button end ;


    //使用handler时首先要创建一个handler
    Handler mHandler = new Handler();
    //要用handler来处理多线程可以使用runnable接口，这里先定义该接口
    //线程中运行该接口的run函数
    Runnable update_thread = new Runnable()
    {
        public void run()
        {
            //线程每次执行时输出"UpdateThread..."文字,且自动换行
            //textview的append功能和Qt中的append类似，不会覆盖前面的内容，只是Qt中的append默认是自动换行模式
            text_view.append("\nUpdateThread...");
            //延时1s后又将线程加入到线程队列中
            mHandler.postDelayed(update_thread, 1000);


        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_view = (TextView)findViewById(R.id.text_view);
        start = (Button)findViewById(R.id.start);
        start.setOnClickListener(new StartClickListener());
        end = (Button)findViewById(R.id.end);
        end.setOnClickListener(new EndClickListener());

    }

    // 开始线程
    private class StartClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //将线程接口立刻送到线程队列中
            mHandler.post(update_thread);
        }
    }

    // 结束线程
    private class EndClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            //将接口从线程队列中移除
            mHandler.removeCallbacks(update_thread);
        }
    }
}
