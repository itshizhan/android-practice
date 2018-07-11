package com.itshizhan.activitytest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class FirstActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
    }

    public void btnClickHandle(View view) {

        // Toast.makeText(this,"you click button! ", Toast.LENGTH_SHORT).show();
        // Intent intent = new Intent(FirstActivity.this,SecondActivity.class);

        // 每个intent 只能指定一个action，却能指定多个catagory；

        if(view.getId() == R.id.btn_a){
            //Intent intent = new Intent("com.itshizhan.activitytest.ACTION_START");
            //intent.addCategory("com.itshizhan.activitytest.MY_CATEGORY");

            Intent intent = new Intent(FirstActivity.this,ThirdActivity.class);

            startActivity(intent);
            //startActivityForResult();
        }else if (view.getId() == R.id.btn_b){

            // Intent.ACTION_VIEW 属于系统内置动作
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("http://baidu.com"));
            startActivity(intent);
        }else if(view.getId() == R.id.btn_c){
            Intent intent = new Intent(FirstActivity.this,ThirdActivity.class);
            // key-value
            intent.putExtra("extra_data","我是Activity One 携带的数据--one");
            startActivity(intent);
        }else if(view.getId() == R.id.btn_d){
            Intent intent = new Intent(FirstActivity.this,ThirdActivity.class);
            // key-value
            intent.putExtra("extra_data","我是Activity One 携带的数据--two");
            startActivityForResult(intent,1);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        // inflate 获取当前菜单
        getMenuInflater().inflate(R.menu.main,menu);
        // 允许菜单显示出来
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.add_item:
                Toast.makeText(FirstActivity.this,item.getTitle(),Toast.LENGTH_SHORT).show();
                break;

            case R.id.remove_item:
                Toast.makeText(FirstActivity.this,item.getTitle(),Toast.LENGTH_SHORT).show();
                //finish(); // 销毁当前活动activity
                break;
            default:
                break;
        }
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == 1){
            String retData = data.getStringExtra("extra_activityThree");
            Toast.makeText(FirstActivity.this, "返回数据了：" + retData, Toast.LENGTH_SHORT).show();

        }
    }
}
