package com.itshizhan.basicwidgedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SeekBar mSeekBar = null;
    private TextView mPromptTv, mProgressTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSeekBar = findViewById(R.id.seekBar);
        mPromptTv = (TextView) findViewById(R.id.prompt_tv);
        mProgressTv = (TextView) findViewById(R.id.pb_tv);



        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mPromptTv.setText("正在拖动");
                mProgressTv.setText("当前数值:" + progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mPromptTv.setText("开始拖动");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mPromptTv.setText("停止拖动");

            }
        });
    }
}
