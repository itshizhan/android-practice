package com.itshizhan.listviewmodule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class CustomArrayAdapterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_arrayadapter_layout);
        // 获取界面组件
        ListView listView = (ListView) findViewById(R.id.listview);

        // 定义要显示的数组
        String[] contents = {"Android", "demo", "Edit", "APP", "excel",
                "dota", "Button", "Circle", "excel", "back"};

        // 将数组包装为自定义MyArrayAdapter
        MyArrayAdapter adapter = new MyArrayAdapter(this, R.layout.custom_arrayadapter_item, contents);

        // 为ListView设置Adapter
        listView.setAdapter(adapter);
    }
}
