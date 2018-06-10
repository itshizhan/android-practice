package com.itshizhan.handlerdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class HandlerThree extends AppCompatActivity {

    private ImageView mImageView;
    private int[] images = {
            R.drawable.icon111,
            R.drawable.icon222,
            R.drawable.icon333
    };
    private Handler mHandler = new Handler();

    private int index;

    class MyRunnable implements Runnable {

        @Override
        public void run() {
            index++;
            index = index % 3;
            mImageView.setImageResource(images[index]);
            //每个1s执行,以实现每1秒实现一次的定时器操作
            mHandler.postDelayed(new MyRunnable(), 1000);
        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_three);

        mImageView = this.<ImageView>findViewById(R.id.image_view);

        //每个1s执行
        mHandler.postDelayed(new MyRunnable(), 1000);

    }
}
