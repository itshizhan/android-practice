package com.itshizhan.gridviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private GridView mAppGridView = null;
    // 应用图标
    private int[] mAppIcons = {
            R.drawable.app1, R.drawable.app2, R.drawable.app3,
            R.drawable.app4, R.drawable.app5, R.drawable.app6,
            R.drawable.app7, R.drawable.app2, R.drawable.app1
    };
    // 应用名
    private String[] mAppNames = {
            "魔法棒", "点赞社群", "购物街区","蚂蚁社区","鑫鱻地图",
            "鑫鱻消息", "房品汇","商城","模型盒子"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 获取界面组件
        mAppGridView = (GridView) findViewById(R.id.gridview);

        // 初始化数据，创建一个List对象，List对象的元素是Map
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        for(int i =0;i<mAppIcons.length;i++){
            Map<String, Object> listItem = new HashMap<String, Object>();
            listItem.put("icon",mAppIcons[i]);
            listItem.put("name",mAppNames[i]);
            list.add(listItem);
        }

        // 创建一个SimpleAdapter
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,
            list,
            R.layout.gridview_item,
            new String[]{"icon", "name"},
            new int[]{R.id.icon_img, R.id.name_tv});

        mAppGridView.setAdapter(simpleAdapter);

        // 添加列表项被单击的监听器
        mAppGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 显示被单击的图片
                Toast.makeText(MainActivity.this, mAppNames[position],
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
