package com.itshizhan.imageviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ZoomButton;
import android.widget.ZoomControls;

public class MainActivity extends AppCompatActivity {
    private ZoomButton mMinusZb = null; // 缩小按钮
    private ZoomButton mPlusZb = null; // 放大按钮
    private ZoomControls mControlZc = null; //缩放组件

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 获取界面组件
        mMinusZb = (ZoomButton) findViewById(R.id.minus_zb);
        mPlusZb = (ZoomButton) findViewById(R.id.plus_zb);
        mControlZc = (ZoomControls) findViewById(R.id.control_zc);



        // 为缩小按钮绑定OnClickListener监听器
        mMinusZb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("MainActivity-mMinusZb","缩小");
                Toast.makeText(MainActivity.this, "缩小", Toast.LENGTH_SHORT).show();
            }
        });
        // 为放大按钮绑定OnClickListener监听器
        mPlusZb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "放大", Toast.LENGTH_SHORT).show();
            }
        });

        // 为缩放组件绑定OnZoomInClickListener监听器
        mControlZc.setOnZoomInClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "放大", Toast.LENGTH_SHORT).show();
            }
        });
        // 为缩放组件绑定OnZoomOutClickListener监听器
        mControlZc.setOnZoomOutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "缩小", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
