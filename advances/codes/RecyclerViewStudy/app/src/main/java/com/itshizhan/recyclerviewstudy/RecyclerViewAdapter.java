package com.itshizhan.recyclerviewstudy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<String> mDatas = null;
    private LayoutInflater mInflater = null;

    public RecyclerViewAdapter(Context context, ArrayList<String> datas) {
        this.mDatas = datas;
        this.mInflater = LayoutInflater.from(context);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        ViewHolder vewHolder = new ViewHolder(view);

        return vewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String name = mDatas.get(position);
        holder.titleTv.setText("Title " + name);
        holder.contenTv.setText("content " + name);

    }


    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTv = null;
        public TextView contenTv = null;

        public ViewHolder(View itemView) {
            super(itemView);

            titleTv = (TextView) itemView.findViewById(R.id.title_tv);
            contenTv = (TextView) itemView.findViewById(R.id.content_tv);
        }
    }
}
