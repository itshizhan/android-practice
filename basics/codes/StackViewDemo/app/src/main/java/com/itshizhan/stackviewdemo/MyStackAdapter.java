package com.itshizhan.stackviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class MyStackAdapter extends BaseAdapter {

    private Context mContext = null;
    private int[] mImageIds = null;


    public MyStackAdapter(Context context,int[] mImageIds ) {
        this.mContext = context;
        this.mImageIds = mImageIds;
    }


    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return mImageIds.length;
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return position;
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = null;
        if(null == convertView) {
            // 创建一个ImageView
            imageView = new ImageView(mContext);
            // 设置ImageView的缩放类型
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            // 为imageView设置布局参数
            imageView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            //convertView = imageView;
        } else {
            imageView = (ImageView) convertView;
        }

        // 给ImageView设置图片资源
        imageView.setImageResource(mImageIds[position]);


        return  imageView;
    }
}
