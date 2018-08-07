package com.itshizhan.servicebestpractice;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private DownloadService.DownloadBinder  mDownloadBinder;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mDownloadBinder = (DownloadService.DownloadBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button start = findViewById(R.id.start_download);
        Button cancel = findViewById(R.id.canceled_download);
        Button paused = findViewById(R.id.paused_download);
        start.setOnClickListener(this);
        cancel.setOnClickListener(this);
        paused.setOnClickListener(this);

        Intent intent = new Intent(this,DownloadService.class);
        // 启动服务
        startService(intent);
        bindService(intent,connection,BIND_AUTO_CREATE);
        if(ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            },1);
        }


    }

    @Override
    public void onClick(View v) {
        if(mDownloadBinder == null){
            return;
        }
        switch (v.getId()){
            case R.id.start_download:
                String url = "https://github.com/square/okhttp/archive/parent-3.10.0.zip";
                mDownloadBinder.startDownload(url);
                break;
            case R.id.paused_download:
                mDownloadBinder.pausedDownload();
                break;
            case R.id.canceled_download:
                mDownloadBinder.cancelDownload();
                break;
            default:
                break;
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1){
            if(grantResults.length>0&&grantResults[0]!=PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"拒绝权限将无法使用程序",Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}
