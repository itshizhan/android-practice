package com.itshizhan.recyclerviewstudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView = null;
    private RecyclerViewAdapter mAdapter = null;
    private ArrayList<String> mDatas = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // 获取组件
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        // 设置管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        // 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);

        // 初始化列表数据
        initDatas();

        // 设置适配器
        mAdapter = new RecyclerViewAdapter(this, mDatas);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initDatas() {
        mDatas = new ArrayList<>();


        for (int i = 0; i < 50; i++) {
            mDatas.add(i, i + 1 + "");
        }
    }
}
