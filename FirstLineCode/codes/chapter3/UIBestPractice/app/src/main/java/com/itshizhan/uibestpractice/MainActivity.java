package com.itshizhan.uibestpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Msg> mMsgList  = new ArrayList<>();
    private EditText inputText;
    private Button send;
    private RecyclerView msgRecyclerView;
    private MsgAdapter mMsgAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 初始化消息
        initMsgs();
        inputText = findViewById(R.id.input_text);
        send = findViewById(R.id.send);
        msgRecyclerView = findViewById(R.id.msg_recycler_view);

        // 布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(linearLayoutManager);
        mMsgAdapter = new MsgAdapter(mMsgList);
        msgRecyclerView.setAdapter(mMsgAdapter);

        // send 按钮点击发送消息
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputText.getText().toString();
                if(!"".equals(content)){
                    Msg msg = new Msg(content,Msg.TYPE_RECEIVED);
                    mMsgList.add(msg);

                    // 当有新消息是，属性view
                    mMsgAdapter.notifyItemInserted(mMsgList.size()-1);
                    msgRecyclerView.scrollToPosition(mMsgList.size()-1);
                    inputText.setText("");
                }

            }
        });

    }

    private void initMsgs() {
        Msg msg1 = new Msg("Hello guy",Msg.TYPE_RECEIVED);
        mMsgList.add(msg1);
        Msg msg2 = new Msg("Hello. Who is that?",Msg.TYPE_SEND);
        mMsgList.add(msg2);
        Msg msg3 = new Msg("This is Tom,Nice talking to you",Msg.TYPE_RECEIVED);
        mMsgList.add(msg3);

    }
}
