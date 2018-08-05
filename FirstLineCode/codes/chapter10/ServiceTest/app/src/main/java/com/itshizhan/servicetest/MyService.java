package com.itshizhan.servicetest;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    private static final String TAG = "MyService";
    public MyService() {
        Log.d(TAG,"MyService  constructor");
    }


    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG,"MyService  onBind");
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return  null;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"MyService  onCreate");
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
