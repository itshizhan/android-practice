package com.itshizhan.listviewmodule;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;

public class MyListActivity extends ListActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 定义一个数组
        String[] persons = {"蜡笔小新", "皮卡丘", "蜘蛛侠", "灰太狼", "黑猫警长",
                "孙悟空", "忍者神龟", "米老鼠", "HelloKitty", "樱桃小丸子"};

        // 将数组包装成ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_single_choice, persons);

        // 设置该窗口显示列表
        setListAdapter(adapter);
    }
}
