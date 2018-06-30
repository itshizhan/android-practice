package com.itshizhan.numberpickerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.NumberPicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private NumberPicker mNumberPicker = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNumberPicker = findViewById(R.id.numberPicker);
        // 设置NumberPicker属性
        mNumberPicker.setMinValue(1);
        mNumberPicker.setMaxValue(20);
        mNumberPicker.setValue(6);

        // 监听数值改变事件
        mNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                Toast.makeText(MainActivity.this, "选择的是：" + newVal,
                        Toast.LENGTH_SHORT).show();
            }
        });

        mNumberPicker.setOnScrollListener(new NumberPicker.OnScrollListener() {
            @Override
            public void onScrollStateChange(NumberPicker view, int scrollState) {
                Toast.makeText(MainActivity.this, "选择的是：" + scrollState,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
