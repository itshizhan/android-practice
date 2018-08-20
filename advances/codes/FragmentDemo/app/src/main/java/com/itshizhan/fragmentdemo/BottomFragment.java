package com.itshizhan.fragmentdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class BottomFragment extends Fragment {

    Bundle bundle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        // 设置布局文件
        View view =  inflater.inflate(R.layout.fragment,container,false);

        final TextView textView = view.findViewById(R.id.fragment_text);
        final Button button = view.findViewById(R.id.fragment_button);
        bundle = this.getArguments();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textView.setText(bundle.getString("message"));

            }
        });

        return view;


    }
}
