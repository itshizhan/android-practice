package com.itshizhan.fragmentdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BottomFragmentTwo extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_two,container,false);

    }

    // 设置接口回调放方法
    public void sendMessage(ICallBack iCallBack){
        iCallBack.get_message_from_Fragment("消息，我来自Fragment");
    }
}
