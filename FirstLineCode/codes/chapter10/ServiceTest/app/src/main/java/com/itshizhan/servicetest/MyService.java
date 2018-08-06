package com.itshizhan.servicetest;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class MyService extends Service {

    private static final String TAG = "MyService";



    public MyService() {
        Log.d(TAG,"MyService  constructor");
    }


    class DownloadBinder extends Binder{
        public void startDownload(){
            Log.d(TAG,"startDownload  executed");
        }
        public int getProgress(){
            Log.d(TAG,"getProgress  executed");
            return 0;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG,"MyService  onBind");
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return  new DownloadBinder();
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"MyService  onCreate");
        // 创建前台服务
        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this,0,intent,0);
        Notification notification = new NotificationCompat.Builder(this,"default")
                .setContentTitle("this is service title")
                .setContentText("This is service content")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentIntent(pi)
                .build();
       startForeground(1,notification);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"MyService  onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"MyService  onDestroy");
    }
}
