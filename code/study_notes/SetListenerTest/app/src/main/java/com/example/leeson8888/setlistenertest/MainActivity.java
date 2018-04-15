package com.example.leeson8888.setlistenertest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //2: 使用匿名内容类设置事件监听
        Button btn2 = (Button) findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"点击了第二个按钮,id是："+v.getId(),Toast.LENGTH_SHORT).show();
            }
        });

        // 3:使用外部类：
        /*
        *  - 创建外部类，并且实现 OnClickListener 接口，重写这个接口中的 OnClick 方法
        *  - 获取按钮，通过setOnClickListener 绑定事件，传入外部类实例 new 外部类(this)
        */

        Button btn3 = (Button) findViewById(R.id.button3);
        btn3.setOnClickListener(new ListenerFn(this));

        // 5:使用成员内部类
        Button btn4 = (Button) findViewById(R.id.button4);
        btn4.setOnClickListener(new ListenerFn1());

    }

    // 1: 直接使用onClick属性，设置事件监听
    public void listnerFn1(View v){
        Toast.makeText(MainActivity.this,"点击了第一个按钮,id是："+v.getId(),Toast.LENGTH_SHORT).show();
    }


    private  class  ListenerFn1 implements View.OnClickListener{

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            Toast.makeText(MainActivity.this,"点击了第四个按钮，成员内部类的方式,id是："+v.getId(),Toast.LENGTH_SHORT).show();

        }
    }
}


//4: 使用MainActivity直接实现OnClickListener接口的方式,并且重写 OnClick 方法
