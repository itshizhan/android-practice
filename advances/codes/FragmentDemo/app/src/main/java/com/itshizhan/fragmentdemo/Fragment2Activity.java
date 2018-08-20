package com.itshizhan.fragmentdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Fragment2Activity extends AppCompatActivity {
    Button button;
    TextView text;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_2_activity);

        button = (Button)findViewById(R.id.button);
        text = (TextView)findViewById(R.id.text);

        // 步骤1：获取FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();

        // 步骤2：获取FragmentTransaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // 步骤3：创建需要添加的Fragment
        final BottomFragmentTwo fragmentTwo = new BottomFragmentTwo();

        // 步骤4：动态添加fragment
        // 即将创建的fragment添加到Activity布局文件中定义的占位符中（FrameLayout）
        fragmentTransaction.add(R.id.fragment_container_two, fragmentTwo);
        fragmentTransaction.commit();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 回调中实现接口方法
                fragmentTwo.sendMessage(new ICallBack() {
                    @Override
                    public void get_message_from_Fragment(String str) {
                        text.setText(str);
                    }
                });
            }
        });




    }
}
