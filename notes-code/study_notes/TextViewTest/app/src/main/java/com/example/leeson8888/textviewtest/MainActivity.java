package com.example.leeson8888.textviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.leeson8888.textviewtest.R.id.text_view;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(text_view);
        textView.setText("hello android！");

        //2 : 使用匿名内容部类

    }

    // 1: 使用onClick属性直接进行事件绑定
    public void listenerFn1(View v){
        Toast.makeText(MainActivity.this,"点击了按钮1",Toast.LENGTH_SHORT).show();
    }
}
