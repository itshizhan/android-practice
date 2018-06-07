package com.itshizhan.recyclerviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

//创建适配器Adapter
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<String> mDatas;
    private LayoutInflater mLayoutInflater;

    // 构造方法
    public RecyclerViewAdapter(Context context,ArrayList<String> arrayList) {
        this.mDatas = arrayList;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

   // 创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.recyclerview_item,parent,false);
        return new ViewHolder(view);
    }


    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String name = mDatas.get(position);
        holder.mTitleTv.setText("Title " + name);
        holder.mContentTv.setText("content " + name);

        /*
        int padding = Math.abs(new Random().nextInt() % 100);
        holder.mTitleTv.setPadding(0, padding, 0, 0);
        holder.mContentTv.setPadding(0, 0, 0, padding);
        */

    }

    // 获取数据的数量
    @Override
    public int getItemCount() {
        return mDatas == null?0:mDatas.size();
    }

    //// 自定义的ViewHolder，持有每个Item的的所有界面组件
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTitleTv;
        TextView mContentTv;

        ViewHolder(View itemView) {
            super(itemView);
            mTitleTv = (TextView) itemView.findViewById(R.id.title_tv);
            mContentTv = (TextView) itemView.findViewById(R.id.content_tv);
        }
    }
}
