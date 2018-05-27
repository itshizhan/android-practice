package com.itshizhan.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.itshizhan.criminalintent.database.CrimeBaseHelper;
import com.itshizhan.criminalintent.database.CrimeCursorWrapper;
import com.itshizhan.criminalintent.database.CrimeDbSchema.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.itshizhan.criminalintent.database.CrimeDbSchema.CrimeTable.Cols.UUID;
import static com.itshizhan.criminalintent.database.CrimeDbSchema.CrimeTable.Cols.DATE;
import static com.itshizhan.criminalintent.database.CrimeDbSchema.CrimeTable.Cols.SOLVED;
import static com.itshizhan.criminalintent.database.CrimeDbSchema.CrimeTable.Cols.TITLE;
import static com.itshizhan.criminalintent.database.CrimeDbSchema.CrimeTable.Cols.SUSPECT;
// 单例： 私有构造方法和 get 方法
public class CrimeLab {
    private static CrimeLab sCrimeLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }

        return sCrimeLab;
    }

    private CrimeLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mContext)
                .getWritableDatabase();

    }

    public void addCrime(Crime c) {
        ContentValues values = getContentValues(c);
        mDatabase.insert(CrimeTable.NAME, null, values);
    }

    public List<Crime> getCrimes() {
        List<Crime> crimes = new ArrayList<>();
        CrimeCursorWrapper cursor = queryCrimes(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                crimes.add(cursor.getCrime());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return crimes;
    }

    public Crime getCrime(UUID id) {
        CrimeCursorWrapper cursor = queryCrimes(
                CrimeTable.Cols.UUID + " = ?",
                new String[]{id.toString()}
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getCrime();
        } finally {
            cursor.close();
        }
    }

    // 获取照片文件的存储路径
    public File getPhotoFile(Crime crime) {
        // getFilesDir: 获取/data/data/<包名>/files目录
        // File getDir(String name, int mode): 获取/data/data/<包名>/目录的子目录(如果不存在就先创建它)。
        File filesDir = mContext.getFilesDir();
        return new File(filesDir, crime.getPhotoFilename());
    }

    public void updateCrime(Crime crime) {
        String uuidString = crime.getId().toString();
        ContentValues values = getContentValues(crime);
        mDatabase.update(CrimeTable.NAME, values,
                CrimeTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }

    private CrimeCursorWrapper queryCrimes(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                CrimeTable.NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null  // orderBy
        );
        return new CrimeCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(Crime crime) {
        ContentValues values = new ContentValues();
        values.put(UUID, crime.getId().toString());
        values.put(TITLE, crime.getTitle());
        values.put(DATE, crime.getDate().getTime());
        values.put(SOLVED, crime.isSolved() ? 1 : 0);
        values.put(SUSPECT, crime.getSuspect());

        return values;
    }

}
