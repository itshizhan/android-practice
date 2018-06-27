package com.itshizhan.stackviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.StackView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private StackView mStackView = null;
    private Button mPrevBtn = null;
    private Button mNextBtn = null;
    private int[] mImageIds = {
            R.drawable.qq201701103, R.drawable.qq201701102, R.drawable.qq201701101
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStackView  = findViewById(R.id.stackview);
        mPrevBtn = (Button) findViewById(R.id.prev_btn);
        mNextBtn = (Button) findViewById(R.id.next_btn);


        MyStackAdapter myStackAdapter = new MyStackAdapter(this,mImageIds);
        mStackView.setAdapter(myStackAdapter);

        // 为三个按钮设置点击事件监听器
        mPrevBtn.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);


    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.prev_btn:
                mStackView.showPrevious();
                break;
            case R.id.next_btn:
                mStackView.showNext();
                break;
            default:
                break;

        }
    }
}
