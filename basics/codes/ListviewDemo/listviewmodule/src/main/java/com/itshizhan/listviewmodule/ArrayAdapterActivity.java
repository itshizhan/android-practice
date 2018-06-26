package com.itshizhan.listviewmodule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ArrayAdapterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arrayadapter_layout);
        // 获取界面ListView组件
        ListView listView = (ListView) findViewById(R.id.listview);

        final String[] books = {
                "初识Android开发", "Android初级开发", "Android中级开发",
                "Android高级开发", "Android开发进阶", "Android项目实战", "Android企业级开发"
        };
        // 将数组包装成ArrayAdapter

        /****************

        context：要使用的上下文环境，几乎创建所有组件都需要传入Context对象。
        resource： 要使用的视图资源 ID，该视图将作为ArrayAdapter的列表项组件。
        这里使用了Android系统中自带的视图资源，系统预定义的视图资源主要有以下几种：

        android.R.layout.simple_list_item_1: 单独一行的文本框。
        android.R.layout.simple_list_item_2: 两个文本框组成。
        android.R.layout.simple_list_item_checked: 每项都是由一个已选中的列表项。
        android.R.layout.simple_list_item_multiple_choice: 都带有一个复选框。
        android.R.layout.simple_list_item_single_choice: 都带有一个单选钮。

        objects：要实际显示的数组或List，将负责为多个列表项提供数据。 该数组或List包含多少个元素，就将生成多少个列表项。
         */

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, books);

        // 为ListView设置Adapter
        listView.setAdapter(arrayAdapter);

        //为ListView绑定列表项点击事件监听器
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int num = position +1;
                Toast.makeText(ArrayAdapterActivity.this,
                        "点击了Item"+ num + ":"+books[position], Toast.LENGTH_SHORT).show();
            }
        });

    }
}
