package com.example.leeson8888.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

//AppCompatActivity 是Activity 的子类
//compatibility 兼容性，互换性

public class MainActivity extends AppCompatActivity {

   // private  static  final  String TAG="dsd";

    //onCreate方法，是活动创建时必须执行的方法
    //android 开发讲究逻辑和视图分离，所以一般不在活动中定义视图。
    //通常是在布局中编写界面，在活动中引入进来
    //setContentView 方法即是引入布局视图,如：activity_main  layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MainActivity:","onCreate start--------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("itshizhan:","itshizhan start--------------------------------");

    }
}
