package com.itshizhan.eventhandledemotwo;


import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import android.widget.Toast;


public class MyButton  extends android.support.v7.widget.AppCompatButton {

    public MyButton(Context context, AttributeSet attrs) {
        super(context,attrs);
    }

    //重写方法

    @Override
    public boolean onTouchEvent(MotionEvent event) {
         super.onTouchEvent(event);
        //消息提示
        Toast.makeText(getContext(), "MyButton回调onTouchEvent方法", Toast.LENGTH_SHORT).show();

        //返回true，表明该事件不会向外扩散
        return true;
    }
}
