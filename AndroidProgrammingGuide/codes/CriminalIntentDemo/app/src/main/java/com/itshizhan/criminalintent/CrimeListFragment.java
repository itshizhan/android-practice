package com.itshizhan.criminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CrimeListFragment extends Fragment {
    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;
    private int mPosition;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 告知fragmentManger，应接受菜单选项方法
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //return super.onCreateView(inflater, container, savedInstanceState);
        //没有LayoutManager的支持，不仅RecyclerView无法工作，还会导致应用崩溃。
        View view = inflater.inflate(R.layout.fragment_crime_list,container,false);
        mCrimeRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);
        //LinearLayoutManager类，它支持以竖直列表的形式展示列表项。
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;

    }

    //要保证fragment视图得到刷新，在onResume()方法内更新代码是最安全的选择
    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }


    //创建菜单
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_crime_list,menu);

    }
    // 菜单事件



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.new_crime:
                Crime crime = new Crime();
                CrimeLab.get(getActivity()).addCrime(crime);
                Intent intent = CrimePagerActivity.newIntent(getActivity(),crime.getId());
                startActivity(intent);
                return  true;
            case R.id.show_subtitle:
                updateSubtitle();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void updateSubtitle() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        int crimeCount = crimeLab.getCrimes().size();
        String subtitle = getString(R.string.subtitle_format, crimeCount);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(subtitle);
    }

    private void updateUI() {
        //获取数据
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();
        //关联数据
        if(mAdapter==null){
            mAdapter = new CrimeAdapter(crimes);
            mCrimeRecyclerView.setAdapter(mAdapter);
        }else{
            // mAdapter.notifyDataSetChanged();
            mAdapter.notifyItemChanged(mPosition);
        }


    }



    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>{
        private List<Crime> mCrimes;

        public CrimeAdapter(List<Crime>crimes){
            mCrimes = crimes;
        }

        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new CrimeHolder(layoutInflater,parent);

        }


        /**
         * 数据绑定
         * @param holder
         * @param position
         */

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            //mPosition = position;
            Crime crime  = mCrimes.get(position);
            holder.bind(crime);

        }


        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }

    // 内部类
    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private Crime mCrime;
        private ImageView mSolvedImageView;
        public static final String EXTRA_CRIME_ID = "com.bignerdranch.android.criminalintent.crime_id";

        // 构造方法
        private CrimeHolder(LayoutInflater inflater,ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_crime,parent,false));
            mTitleTextView = (TextView) itemView.findViewById(R.id.crime_title);
            mDateTextView = (TextView) itemView.findViewById(R.id.crime_date);
            mSolvedImageView = (ImageView) itemView.findViewById(R.id.crime_solved);
            // 绑定点击事件
            itemView.setOnClickListener(this);

        }

        public void bind(Crime crime){
            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());
            String date = (String) DateFormat.format("yyyy年MM月dd日", mCrime.getDate());
            //mDateTextView.setText(mCrime.getDate().toString());
            mDateTextView.setText(date);
            mSolvedImageView.setVisibility(crime.isSolved()?View.VISIBLE:View.GONE);
        }

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {

            //Toast.makeText(getActivity(),mCrime.getTitle()+" clicked",Toast.LENGTH_SHORT).show();
            //Intent intent  = new Intent(getActivity(),CrimeActivity.class);
            //intent.putExtra(EXTRA_CRIME_ID,mCrime.getId());
            //启动CrimeActivity
            //Intent intent = CrimeActivity.newIntent(getActivity(), mCrime.getId());
            // 启动CrimePagerActivity
            Intent intent = CrimePagerActivity.newIntent(getActivity(),mCrime.getId());
            mPosition = this.getAdapterPosition();
            startActivity(intent);
        }


    }

}
