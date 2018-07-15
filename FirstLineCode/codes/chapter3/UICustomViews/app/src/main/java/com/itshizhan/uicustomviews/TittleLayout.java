package com.itshizhan.uicustomviews;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class TittleLayout extends LinearLayout {
    public TittleLayout(Context context, AttributeSet attributeSet) {
        super(context,attributeSet);

        LayoutInflater.from(context).inflate(R.layout.title,this);

        Button backBtn = (Button)findViewById(R.id.title_back);
        backBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ((Activity)getContext()).finish();
                //Toast.makeText(getContext(),"you click",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
