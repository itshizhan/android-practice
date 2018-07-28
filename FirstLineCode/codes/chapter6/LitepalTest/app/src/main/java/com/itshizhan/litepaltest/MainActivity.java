package com.itshizhan.litepaltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void HandleData(View view) {
        switch (view.getId()){
            case R.id.create_database:
                // 创建数据库
                Log.d(TAG,"创建数据库及表");
                Connector.getDatabase();
                break;
            case R.id.add_data:
                Log.d(TAG,"添加数据");
                Book book = new Book();
                book.setAnthor("Tom");
                book.setName("基业长青");
                book.setPages(511);
                book.setPrice(19.99);
                book.save();
                break;
            case R.id.query_data:
                Log.d(TAG,"查询数据");
                List<Book> list = LitePal.findAll(Book.class);
                for(Book item:list){
                    Log.d(TAG,"this name is:"+item.getName());
                    Log.d(TAG,"this.price is:"+ item.getPrice());
                    Log.d(TAG,"this.author is:"+ item.getAnthor());
                    Log.d(TAG,"this.pages is:"+ item.getPages());
                }


                break;
            default:
                break;

        }
    }
}
