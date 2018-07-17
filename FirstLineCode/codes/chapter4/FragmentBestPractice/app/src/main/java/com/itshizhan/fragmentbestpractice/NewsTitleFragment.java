package com.itshizhan.fragmentbestpractice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewsTitleFragment extends Fragment {
    private boolean isTwoPane;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view =
                inflater.inflate(R.layout.news_title_frag,container,false);
        RecyclerView recyclerView =
                (RecyclerView)view.findViewById(R.id.news_title_recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        NewsAdapter newsAdapter = new NewsAdapter(getNews());
        recyclerView.setAdapter(newsAdapter);


        return view;

    }

    private List<News> getNews() {

        List<News> newsList = new ArrayList<>();
        for(int i=1;i<50;i++){
            News news = new News();
            news.setTitle("This is news title"+ i);
            news.setContent(getRandomLengthContent("This is news content"+i));
            newsList.add(news);
        }
        return  newsList;
    }

    private String getRandomLengthContent(String s) {
        Random random = new Random();
        int len = random.nextInt(10)+1;
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<len;i++){
            stringBuilder.append(s);
        }
        return  stringBuilder.toString();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getActivity().findViewById(R.id.news_content_layout)!=null){
            isTwoPane = true;
        }else{
            isTwoPane = false;
        }
    }

    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{

        private List<News> mNewsList;

        public NewsAdapter(List<News> newsList) {
            mNewsList = newsList;
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //return null;
            View view =
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.news_title_item,parent,false);
            final ViewHolder  viewHolder = new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    News news = mNewsList.get(viewHolder.getAdapterPosition());
                    if(isTwoPane){
                        // 平板双页
                        NewsContentFragment newsContentFragment =
                                (NewsContentFragment)getFragmentManager().findFragmentById(R.id.news_content_fragment);
                        newsContentFragment.refresh(news.getTitle(),news.getContent());
                    }else {
                        // 单页模式,启动新的Activity
                        NewsContentActivity.actionStart(getActivity(),
                                news.getTitle(),news.getContent());

                    }
                }
            });

            return viewHolder;

        }


        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            News news = mNewsList.get(position);
            holder.newsTitleText.setText(news.getTitle());
        }


        @Override
        public int getItemCount() {
            return mNewsList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            TextView newsTitleText;

            public ViewHolder(View view) {
                super(view);
                newsTitleText = view.findViewById(R.id.news_title);

            }
        }
    }


}
