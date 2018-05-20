package com.itshizhan.criminalintent;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.UUID;

//AppCompatActivity是FragmentActivity的子类, FragmentActivity是Activity的子类
public class CrimeActivity extends SingleFragmentActivity {

    private static final String EXTRA_CRIME_ID =
            "com.itshizhan.android.criminalintent.crime_id";


    @Override
    protected Fragment createFragment() {
        //return new CrimeFragment();
        UUID crimeId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(crimeId);
    }


    /***
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        //将fragment添加给activity
        FragmentManager fm = getSupportFragmentManager();
        //再获取一个fragment交给它管理
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        //创建并提交了一个fragment事务
        //fragment事务被用来添加、移除、附加、分离或替换fragment队列中的fragment。
        //FragmentManager管理着fragment事务回退栈。
        if (fragment == null) {
            fragment = new CrimeFragment();
            //创 建 并 返 回 FragmentTransaction 实 例 ,FragmentTransaction类支持流接口(fluent interface)的链式方法调用
            fm.beginTransaction()
                 //add参数：容器视图资源ID和新创建的CrimeFragment
                .add(R.id.fragment_container, fragment)
                .commit();

        }


    }

    **/

}
