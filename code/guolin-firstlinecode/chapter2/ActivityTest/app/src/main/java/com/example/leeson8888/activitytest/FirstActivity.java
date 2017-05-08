package com.example.leeson8888.activitytest;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {

    public Button mButton;
    public Button intentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        mButton = (Button)findViewById(R.id.button1);
        mButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //Toast.makeText(FirstActivity.this,"hello button",Toast.LENGTH_SHORT).show();
               // finish();

                /*************** 显示Intent 构造函数 **************
                 * Intent 构造函数第一个参数：启动活动的上下文
                 * Intent 构造函数第二个参数：启动的目标活动
                 */
                //Intent intent = new Intent(FirstActivity.this,SecondActivity.class);
                //startActivity(intent);


                /*************** 隐式Intent 构造函数 **************
                 *  不明确指出需要启动哪个活动
                 *  而是指定一系列抽象的 action 和 category
                 *  由系统分析intent，并启动合适的活动
                 *
                 *  每个intent 只能指定一个action，但是可以指定多个category
                 *  调用intent 的addCategory可以添加多个category
                 */
                Intent intent = new Intent("com.example.leeson8888.activitytest.ACTION_START");
                intent.addCategory("android.intent.category.MyCategory");
                startActivity(intent);


            }
        });

        intentButton = (Button)findViewById(R.id.intent_button);
        intentButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                /************
                 *  ACTION_VIEW 系统内置的动作：对应 android.intent.action.VIEW常量
                 *  Uri.parse  将网址字符串解析为网址Uri对象
                 *  setData() 指定当前intent 操作的数据
                 *
                 *  可以通过<intent-filter></intent-filter>标签配置<data></data>,更精确的指定intent操作的数据
                 *  只有data中指定的数据与Intent 中携带的Data完全一致是，当前活动才能相应intent
                 *
                 */
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);

        //getMenuInflater()得到MenuInflater对象
        //MenuInflater对象的inflater()方法创建当前活动菜单

        getMenuInflater().inflate(R.menu.main,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.item_add:
                Toast.makeText(this,"you click add",Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_remove:
                Toast.makeText(this,"you click remove",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return  true;
    }
}
