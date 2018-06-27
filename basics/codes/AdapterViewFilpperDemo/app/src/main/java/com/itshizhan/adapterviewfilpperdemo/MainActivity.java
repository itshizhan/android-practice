package com.itshizhan.adapterviewfilpperdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterViewFlipper;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private AdapterViewFlipper mFlipper = null;

    private Button mPrevBtn = null;
    private Button mNextBtn = null;
    private Button mAutoBtn = null;

    private int[] mImageIds = {
            R.drawable.qq201701103, R.drawable.qq201701102, R.drawable.qq201701101
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 获取界面组件
        mFlipper = (AdapterViewFlipper) findViewById(R.id.flipper);
        mPrevBtn = (Button) findViewById(R.id.prev_btn);
        mNextBtn = (Button) findViewById(R.id.next_btn);
        mAutoBtn = (Button) findViewById(R.id.auto_btn);

        // 为AdapterViewFlipper设置Adapter
        MyFilpperAdapter adapter = new MyFilpperAdapter(this, mImageIds);
        mFlipper.setAdapter(adapter);

        // 为三个按钮设置点击事件监听器
        mPrevBtn.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);
        mAutoBtn.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.prev_btn:
                // 显示上一个组件
                mFlipper.showPrevious();
                // 停止自动播放
                mFlipper.stopFlipping();
                break;
            case R.id.next_btn:
                // 显示下一个组件。
                mFlipper.showNext();
                // 停止自动播放
                mFlipper.stopFlipping();
                break;
            case R.id.auto_btn:
                // 开始自动播放
                mFlipper.startFlipping();
                break;
            default:
                break;
        }
    }
}
