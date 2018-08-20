package com.itshizhan.fragmentdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Activity2Fragment extends AppCompatActivity {
    TextView text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activcity_2_fragment);

        text = (TextView) findViewById(R.id.text);

        // 动态添加fragment ---- 并在fragment 中携带参数，以传递到Fragment中
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        // 步骤3：创建需要添加的Fragment
        final BottomFragment bottomFragment = new BottomFragment();

        // 步骤4:创建Bundle对象
        // 作用:存储数据，并传递到Fragment中
        Bundle bundle = new Bundle();

        // 步骤5:往bundle中添加数据
        bundle.putString("message", "I love Google");

        // 步骤6:把数据设置到Fragment中
        bottomFragment.setArguments(bundle);


        fragmentTransaction.add(R.id.fragment_container,bottomFragment);
        fragmentTransaction.commit();

    }
}
