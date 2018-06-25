package com.itshizhan.eventhandledemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button mButton;
    Button mBtnTwo;
    Button mBtnThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = findViewById(R.id.btn_one);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"匿名内部类点击了!",Toast.LENGTH_SHORT).show();
            }
        });

        mBtnTwo = findViewById(R.id.btn_two);
        mBtnTwo.setOnClickListener(this);

        mBtnThree = findViewById(R.id.btn_three);
        mBtnThree.setOnClickListener(new InnerListner());
    }

    public void handleClick(View view) {
        Toast.makeText(MainActivity.this,"xml绑定点击了!",Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onClick(View v) {
        Toast.makeText(MainActivity.this,"实现接口绑定点击!",Toast.LENGTH_SHORT).show();
    }

    private class InnerListner implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Toast.makeText(MainActivity.this,"自定义内部类实现接口绑定事件!",Toast.LENGTH_SHORT).show();

        }
    }
}
