package com.itshizhan.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.itshizhan.criminalintent.database.CrimeBaseHelper;
import com.itshizhan.criminalintent.database.CrimeDbSchema.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// 单例： 私有构造方法和 get 方法
public class CrimeLab {
    // 静态变量
    private static CrimeLab sCrimeLab;
    //private List<Crime> mCrimes;
    private Context mContext;
    private SQLiteDatabase mSQLiteDatabase;

    // 初始化数据外部接口
    public static CrimeLab get(Context context){
        if(sCrimeLab==null){
            sCrimeLab = new CrimeLab(context);
        }
        return  sCrimeLab;
    }

    // 创建数据库表字段的值
    private static ContentValues getContentValues(Crime crime){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CrimeTable.Cols.UUID,crime.getId().toString());
        contentValues.put(CrimeTable.Cols.TITLE,crime.getTitle());
        contentValues.put(CrimeTable.Cols.DATE,crime.getDate().getTime());
        contentValues.put(CrimeTable.Cols.SOLVED,crime.isSolved()?1:0);

        return contentValues;
    }
    // 初始化数据底层实现
    private CrimeLab(Context context) {
        //<>符号告诉编译器，List中的元素类型可以基于变量声明传入的抽象参数来确定。
        //Java 7之前，必须这么写:mCrimes = new ArrayList<Crime>();
        //mCrimes = new ArrayList<>();
        mContext = context.getApplicationContext();
        mSQLiteDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();

        /** getWritableDatabase()
         * 打开crimeBase.db数据库，如果不存在则创建
         * 如果是首次创建数据库，就调用onCreate(SQLiteDatabase)方法，然后保存最新的版本号。
         * 如果已创建过数据库，首先检查它的版本号。如果CrimeBaseHelper中的版本号更高，
         * 就调用onUpgrade(SQLiteDatabase, int, int)方法升级
         * onCreate(SQLiteDatabase)方法负责创建初始数据库;
         * onUpgrade- (SQLiteDatabase, int, int)方法负责与升级相关的工作。
         * */

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
        return new ArrayList<>();
    }

    // 根据Id获取数据
    public Crime getCrime(UUID id){
        /*
        for(Crime crime:mCrimes){
            if(crime.getId().equals(id)){
                return crime;
            }
        }
        */
        return null;
    }
    //向数据库添加一项新的crime
    public void addCrime(Crime c){
        //mCrimes.add(c);
        ContentValues contentValues = getContentValues(c);
        mSQLiteDatabase.insert(CrimeTable.NAME,null,contentValues);
    }

    //更新数据库记录
    public void updateCrime(Crime crime){
        String uuidString = crime.getId().toString();
        ContentValues contentValues = getContentValues(crime);
        mSQLiteDatabase.update(CrimeTable.NAME,contentValues,CrimeTable.Cols.UUID+" =?",
                new String[]{uuidString});

    }

    //查询数据
    private Cursor queryCrimes(String whereClause,String [] whereArgs){
        Cursor cursor = mSQLiteDatabase.query(
                CrimeTable.NAME,
                null, // null : 查询所有列
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return cursor;
    }

}
