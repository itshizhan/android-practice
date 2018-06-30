package com.itshizhan.viewflipperdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {
    private ViewFlipper mViewFlipper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewFlipper = (ViewFlipper) findViewById(R.id.details);

    }

    public void prev(View view) {
        mViewFlipper.setInAnimation(this, android.R.anim.fade_in);
        mViewFlipper.setOutAnimation(this, android.R.anim.fade_out);
        // 显示上一个组件
        mViewFlipper.showPrevious();
        // 停止自动播放
        mViewFlipper.stopFlipping();
    }

    public void auto(View view) {
        mViewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        mViewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);
        // 开始自动播放
        mViewFlipper.startFlipping();

    }

    public void next(View view) {

        mViewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        mViewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);
        // 显示下一个组件
        mViewFlipper.showNext();
        // 停止自动播放
        mViewFlipper.stopFlipping();
    }
}
