package com.itshizhan.checkboxdemo;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private CheckBox mShanghaiCb;
    private CheckBox mBeijingCb;
    private CheckBox mChongqingCb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 获取界面组件
        mShanghaiCb = (CheckBox) findViewById(R.id.shanghai_cb);
        mBeijingCb = (CheckBox) findViewById(R.id.beijing_cb);
        mChongqingCb = (CheckBox) findViewById(R.id.chongqing_cb);

        mShanghaiCb.setOnCheckedChangeListener(onCheckBoxChangeListener());
        mBeijingCb.setOnCheckedChangeListener(onCheckBoxChangeListener());
        mChongqingCb.setOnCheckedChangeListener(onCheckBoxChangeListener());
    }

    @NonNull
    private CompoundButton.OnCheckedChangeListener onCheckBoxChangeListener() {
        return new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                showSelectCity(compoundButton);
            }
        };
    }

    private void showSelectCity(CompoundButton compoundButton) {
        String city = compoundButton.getText().toString();
        // 根据复选框的选中状态进行相应提示
        if (compoundButton.isChecked()) {
            Toast.makeText(MainActivity.this, "选中" + city,
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "取消选中" + city,
                    Toast.LENGTH_SHORT).show();
        }
    }
}
