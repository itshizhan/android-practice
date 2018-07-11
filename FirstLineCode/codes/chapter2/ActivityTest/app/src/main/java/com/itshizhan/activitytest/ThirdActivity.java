package com.itshizhan.activitytest;

import android.content.Intent;
import android.net.Uri;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import javax.xml.transform.Result;

public class ThirdActivity extends BaseActivity {

    String data;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Intent intent = getIntent();
        data = intent.getStringExtra("extra_data");

        textView = findViewById(R.id.textView);
        textView.setText(data);

    }

    public void btnClickHandle(View view) {
        /* 打开网页
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://baidu.com"));
        */
        // 拨打电话
        /*
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:10086"));
        startActivity(intent);
        */

        if(view.getId() == R.id.button_four){
            //Toast.makeText(ThirdActivity.this,"four",Toast.LENGTH_SHORT).show();
            // 返回数据给上一个页面
            Intent intent = new Intent();
            intent.putExtra("extra_activityThree","我是ActivityThree返回的数据--clickButton");
            // 向上一个页面返回数据
            setResult(RESULT_OK,intent);
            finish();

        }else if(view.getId() == R.id.button_three){
            Toast.makeText(ThirdActivity.this,"three",Toast.LENGTH_SHORT).show();
            ActivityCollector.finishAll();
            //Process.killProcess(Process.myPid());
            //finish();
        }


    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        // 返回数据给上一个页面
        Intent intent = new Intent();
        intent.putExtra("extra_activityThree","我是ActivityThree返回的数据--onBackPress");
        // 向上一个页面返回数据
        setResult(RESULT_OK,intent);
        finish();
    }
}
