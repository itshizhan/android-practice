package com.itshizhan.listviewmodule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CustomItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_item_layout);

        // 获取界面ListView组件
        ListView listView = (ListView) findViewById(R.id.listview);

        // 定义一个List集合
        final List<String> components = new ArrayList<>();
        components.add("TextView");
        components.add("EditText");
        components.add("Button");
        components.add("CheckBox");
        components.add("RadioButton");
        components.add("ToggleButton");
        components.add("ImageView");

        // 将List包装成ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.custom_item, R.id.content_tv, components);

        // 为ListView设置Adapter
        listView.setAdapter(adapter);

        // 为ListView列表项绑定点击事件监听器
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(CustomItemActivity.this, components.get(position),
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}
