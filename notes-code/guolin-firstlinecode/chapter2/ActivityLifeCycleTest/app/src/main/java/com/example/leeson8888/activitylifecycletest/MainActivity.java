package com.example.leeson8888.activitylifecycletest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static  final String Tag1="MainActivityLifeCycle";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(Tag1,"onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startNormalActivity=(Button)findViewById(R.id.start_normal_activity);

        Button startDialogActivity=(Button)findViewById(R.id.start_dialog_activity);

        startNormalActivity.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,NormalActivity.class);
                startActivity(intent);
            }
        });

        startDialogActivity.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,DialogActivity.class);
                startActivity(intent);
            }
        });





    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(Tag1,"onStart");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d(Tag1,"onResume");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d(Tag1,"onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(Tag1,"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(Tag1,"onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(Tag1,"onRestart");
    }
}
