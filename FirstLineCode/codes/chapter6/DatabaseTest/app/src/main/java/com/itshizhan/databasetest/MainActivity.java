package com.itshizhan.databasetest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new MyDatabaseHelper(this,"BookStore.db",null,2);

        Button createDatabaseBtn = (Button) findViewById(R.id.create_database);
        createDatabaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.getWritableDatabase();
            }
        });
    }

    public void HandleData(View view) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        switch (view.getId()){
            case R.id.add_data:
                Toast.makeText(MainActivity.this,"add data",Toast.LENGTH_SHORT).show();
                ContentValues contentValues = new ContentValues();
                // 第一条数据
                contentValues.put("name","The Da Vinci code");
                contentValues.put("author","Dan Brown");
                contentValues.put("pages",454);
                contentValues.put("price",19.66);
                db.insert("book",null,contentValues);
                contentValues.clear();
                contentValues.put("name","The Lost Symbol");
                contentValues.put("author","Dan Brown");
                contentValues.put("pages",510);
                contentValues.put("price",20.66);
                db.insert("book",null,contentValues);
                break;

            case R.id.query_data:
                Toast.makeText(MainActivity.this,"query data",Toast.LENGTH_SHORT).show();
                Log.d(TAG,"查询数据");
                // 查询book表中的所有数据
                Cursor cursor = db.query("book",
                        null, null,null,null,null,null);
                if(cursor.moveToFirst()){
                    do{
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        Log.d(TAG,"book name is:"+ name);
                        Log.d(TAG,"book author is:"+ author);
                        Log.d(TAG,"book pages is:"+ pages);


                    }while (cursor.moveToNext());
                }
                cursor.close();

                break;
        }
    }
}
