package com.example.leeson8888.setlistenertest;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

/**
 * Created by leeson8888 on 2017/7/8.
 */

public class ListenerFn implements View.OnClickListener {

    private Context context;

    public ListenerFn(Context context){
        this.context=context;
    }


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

        Toast.makeText(context, "点击了第三个按钮，外部类的方式",Toast.LENGTH_SHORT).show();
    }
}
