package com.itshizhan.playaudiotest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MediaPlayer mMediaPlayer = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button play = findViewById(R.id.play);
        Button pause = findViewById(R.id.pause);
        Button stop = findViewById(R.id.stop);

        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);

        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{
                 Manifest.permission.WRITE_EXTERNAL_STORAGE
            },1);
        }else{
            initMediaPlayer();
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 1){
            if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                initMediaPlayer();
                finish();
            }
        }
    }

    private void initMediaPlayer() {

        try {
            ///data/hw_init/product/media/Pre-loaded/Music/Polonaise.mp3
            File file = new File(Environment.getExternalStorageDirectory(),"Polonaise.mp3");
            mMediaPlayer.setDataSource(file.getPath());
            mMediaPlayer.prepare(); // 准备播放
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.play:
                mMediaPlayer.start();
                break;
            case R.id.pause:
                mMediaPlayer.pause();
                break;
            case R.id.stop:
                mMediaPlayer.reset();
                initMediaPlayer();
                break;
            default:
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mMediaPlayer!=null){
            mMediaPlayer.stop();
            mMediaPlayer.release();
        }
    }
}
