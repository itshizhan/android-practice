package com.itshizhan.recyclerviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> mDatas;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_layout);
        // 初始化数据
        initDatas();
        // 获取recyclerview
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        // 设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        // 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);

        // 设置适配器
        mAdapter = new RecyclerViewAdapter(this,mDatas);
        mRecyclerView.setAdapter(mAdapter);


    }


    private void initDatas() {
        mDatas = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            mDatas.add(i, i + 1 + "");
        }
    }
}
