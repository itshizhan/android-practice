package com.itshizhan.servicetest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            MyService.DownloadBinder downloadBinder = (MyService.DownloadBinder) service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startBtn = findViewById(R.id.start_service);
        Button stopBtn = findViewById(R.id.stop_service);
        Button bindBtn = findViewById(R.id.bind_service);
        Button unbindBtn = findViewById(R.id.unbind_service);


        startBtn.setOnClickListener(this);
        stopBtn.setOnClickListener(this);
        bindBtn.setOnClickListener(this);
        unbindBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_service:
                Intent startIntent = new Intent(this,MyService.class);
                startService(startIntent);
                break;
            case R.id.stop_service:
                Intent stopIntent = new Intent(this,MyService.class);
                stopService(stopIntent);
                break;
            case R.id.bind_service:
                Intent intent = new Intent(this,MyService.class);
                bindService(intent,mServiceConnection,BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service:
                unbindService(mServiceConnection);

                break;
            default:
                break;
        }
    }
}
