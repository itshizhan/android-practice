package com.itshizhan.radiobuttondemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RadioButton mMaleRb;
    private RadioButton mFemaleRb;
    private RadioGroup mSexRg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 获取界面组件
        mMaleRb = (RadioButton) findViewById(R.id.male_rb);
        mFemaleRb = (RadioButton) findViewById(R.id.female_rb);
        mSexRg = (RadioGroup) findViewById(R.id.sex_rg);

        mSexRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // 获取用户选中的性别
                String sex = "";
                switch (checkedId) {
                    case R.id.male_rb:
                        sex = mMaleRb.getText().toString();

                        break;
                    case R.id.female_rb:
                        sex = mFemaleRb.getText().toString();
                        break;
                    default:break;
                }

                // 消息提示
                Toast.makeText(MainActivity.this,
                        "选择的性别是：" + sex, Toast.LENGTH_SHORT).show();
            }
        });


    }
}
