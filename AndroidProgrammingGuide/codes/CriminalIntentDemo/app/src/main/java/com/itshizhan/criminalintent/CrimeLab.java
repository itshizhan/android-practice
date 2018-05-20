package com.itshizhan.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// 单例： 私有构造方法和 get 方法
public class CrimeLab {
    // 静态变量
    private static CrimeLab sCrimeLab;
    private List<Crime> mCrimes;

    // 初始化数据外部接口
    public static CrimeLab get(Context context){
        if(sCrimeLab==null){
            sCrimeLab = new CrimeLab(context);
        }
        return  sCrimeLab;
    }

    // 初始化数据底层实现
    private CrimeLab(Context context) {
        //<>符号告诉编译器，List中的元素类型可以基于变量声明传入的抽象参数来确定。
        //Java 7之前，必须这么写:mCrimes = new ArrayList<Crime>();
        mCrimes = new ArrayList<>();

        // 暂时生成100个毫无个性的数据
        /*
        for (int i = 0; i < 100; i++) {
            Crime crime = new Crime();
            crime.setTitle("Crime #" + i);
            crime.setSolved(i % 2 == 0);
            mCrimes.add(crime);

        }
        */

    }

    // 获取所有数据
    public List<Crime> getCrimes(){
        return mCrimes;
    }

    // 根据Id获取数据
    public Crime getCrime(UUID id){
        for(Crime crime:mCrimes){
            if(crime.getId().equals(id)){
                return crime;
            }
        }
        return null;
    }
    //添加一项新的crime
    public void addCrime(Crime c){
        mCrimes.add(c);
    }

}
