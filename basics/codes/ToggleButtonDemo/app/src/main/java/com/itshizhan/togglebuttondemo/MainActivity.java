package com.itshizhan.togglebuttondemo;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;


public class MainActivity extends AppCompatActivity {
    private ToggleButton mLikeTb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 获取界面组件
        mLikeTb = (ToggleButton) findViewById(R.id.like_tb);

        // 为开关按钮设置OnCheckedChangeListener监听器
        mLikeTb.setOnCheckedChangeListener(onChange());

    }

    @NonNull
    private CompoundButton.OnCheckedChangeListener onChange() {
        return new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                // 消息提示
                if (compoundButton.isChecked()) {
                    Toast.makeText(MainActivity.this,
                            "喜欢Android开发", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this,
                            "不喜欢Android开发", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

}
