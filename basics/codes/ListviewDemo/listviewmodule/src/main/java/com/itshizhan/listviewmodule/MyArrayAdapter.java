package com.itshizhan.listviewmodule;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyArrayAdapter extends ArrayAdapter {


    private Activity mContext = null; // 上下文环境
    private int mResourceId; // 列表项布局资源ID
    private String[] mItems; // 列表内容数组

    public MyArrayAdapter(@NonNull Activity context, int resId, String[] items) {
        super(context, resId, items);
        mContext = context;
        mResourceId = resId;
        mItems = items;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // super.getView(position, convertView, parent);
        // 获取LayoutInflater对象
        LayoutInflater layoutInflater = mContext.getLayoutInflater();
        // 装载列表项视图
        View itemView = layoutInflater.inflate(mResourceId,null);

        // 获取列表项之组件
        TextView contentTv = (TextView) itemView.findViewById(R.id.content_tv);
        ImageView letterImg = (ImageView) itemView.findViewById(R.id.letter_img);

        // 取出要显示的数据
        String content = mItems[position].trim();

        // 给TextView设置显示值
        contentTv.setText(content);
        // 根据内容首字母判断要显示的图标
        if(content.startsWith("a") || content.startsWith("A")) {
            letterImg.setImageResource(R.drawable.ic_launcher_background);
        } else if(content.startsWith("b") || content.startsWith("B")) {
            letterImg.setImageResource(R.drawable.ic_launcher_foreground);
        } else if(content.startsWith("c") || content.startsWith("C")) {
            letterImg.setImageResource(R.drawable.ic_launcher_background);
        } else if(content.startsWith("d") || content.startsWith("D")) {
            letterImg.setImageResource(R.drawable.ic_launcher_background);
        } else if(content.startsWith("e") || content.startsWith("E")) {
            letterImg.setImageResource(R.drawable.ic_launcher_foreground);
        }

        // 返回列表项视图
        return itemView;


    }
}
