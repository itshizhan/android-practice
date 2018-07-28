package com.itshizhan.sharedpreferencestest;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button saveDataBtn = (Button)findViewById(R.id.save_data);
        Button restoreDataBtn = (Button) findViewById(R.id.restore_data);
        saveDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
                editor.putString("name","Tome");
                editor.putInt("age",30);
                editor.putBoolean("married",false);
                editor.apply();
            }
        });

        restoreDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences  pref = getSharedPreferences("data",MODE_PRIVATE);
                String name = pref.getString("name","");
                Integer age = pref.getInt("age",0);
                Boolean isMarried = pref.getBoolean("married",false);
                Toast.makeText(MainActivity.this,
                        "name:"+ name+"\n"+"age:"+age+"\n"+"isMarried："+isMarried,Toast.LENGTH_LONG).show();


            }
        });
    }
}
