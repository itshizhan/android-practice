package com.itshizhan.viewswitcherdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.itshizhan.viewswitcherdemo.entity.ViewSwitcherItemData;

import java.util.List;

public class ViewSwitcherBaseAdapter extends BaseAdapter {

    private Context mContext = null;

    List<ViewSwitcherItemData> mItemDatas = null;

    public ViewSwitcherBaseAdapter(Context context, List<ViewSwitcherItemData> itemData) {
        mContext = context;
        mItemDatas = itemData;
    }

    @Override
    public int getCount() {
        // 如果已经到了最后一屏，且应用程序的数量不能整除NUMBER_PER_SCREEN
        if (MainActivity.screenNo == MainActivity.screenCount - 1
                && mItemDatas.size() % MainActivity.NUMBER_PER_SCREEN != 0) {
            // 最后一屏显示的程序数为应用程序的数量对NUMBER_PER_SCREEN求余
            return mItemDatas.size() % MainActivity.NUMBER_PER_SCREEN;
        }
        // 否则每屏显示的程序数量为NUMBER_PER_SCREEN
        return MainActivity.NUMBER_PER_SCREEN;
    }


    @Override
    public ViewSwitcherItemData getItem(int position) {
        // 根据screenNo计算第position个列表项的数据
        return mItemDatas.get(
                MainActivity.screenNo * MainActivity.NUMBER_PER_SCREEN + position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (null == convertView) {
            // 加载R.layout.item布局文件
            convertView = LayoutInflater.from(mContext).inflate(R.layout.slide_gridview_item, null);
            holder = new ViewHolder();
            holder.iconImg = (ImageView)convertView.findViewById(R.id.icon_img);
            holder.nameTv = (TextView)convertView.findViewById(R.id.name_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.iconImg.setImageResource(getItem(position).getIcon());
        holder.nameTv.setText(getItem(position).getName());
        return convertView;

    }

    private class ViewHolder{
        ImageView iconImg;
        TextView nameTv;
    }



}
