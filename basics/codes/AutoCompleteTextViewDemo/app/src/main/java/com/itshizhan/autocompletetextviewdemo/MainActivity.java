package com.itshizhan.autocompletetextviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

public class MainActivity extends AppCompatActivity {

    private AutoCompleteTextView mAutoTv = null;
    private MultiAutoCompleteTextView mMultiAutoTv = null;

    // 定义字符串数组，作为提示的文本
    private String[] mContacts = new String[]{
            "test", "abc", "aaa", "aabbcc", "bac", "ok", "say", "aabbsd"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAutoTv = findViewById(R.id.auto_actv);
        mMultiAutoTv = findViewById(R.id.mauto_mactv);

        // 创建一个ArrayAdapter，封装数组
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1, mContacts);

        // 设置Adapter
        mAutoTv.setAdapter(aa);
        mMultiAutoTv.setAdapter(aa);

        // 为MultiAutoCompleteTextView设置分隔符 ,分隔符必须设置的
        mMultiAutoTv.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

    }
}
