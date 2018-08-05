package com.itshizhan.androidthreadtest;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button changeTextBtn;
    TextView mTextView;

    public static final int UPDATE_TEXT = 1;

    private Handler mHandler = new Handler(){
        public void handleMessage(Message message){
            switch (message.what){
                case UPDATE_TEXT:
                    mTextView.setText("更新UI了");
                    break;
                default:
                    break;
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        changeTextBtn = findViewById(R.id.change_text_btn);
        mTextView = findViewById(R.id.text_view);
        changeTextBtn.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    //mTextView.setText("文字改变了");
                    //changeText();
                    Message message = new Message();
                    message.what = UPDATE_TEXT;
                    mHandler.sendMessage(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

    private void changeText() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTextView.setText("文字改变了");
            }
        });
    }
}
