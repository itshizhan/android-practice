package com.itshizhan.spinnerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    private Spinner mProSpinner = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProSpinner = findViewById(R.id.spin_one);

        final String[] books = {
                "初识Android开发", "Android初识开发", "Android中级开发",
                "Android高级开发", "Android开发进阶"
        };

        // 创建ArrayAdapter对象
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, books);
        // 为Spinner设置Adapter
        mProSpinner.setAdapter(adapter);

        // 为Spinner设置选中事件监听器
       mProSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               Toast.makeText(MainActivity.this, "选择的专业是：" + books[position],
                       Toast.LENGTH_SHORT).show();
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });
    }


}
