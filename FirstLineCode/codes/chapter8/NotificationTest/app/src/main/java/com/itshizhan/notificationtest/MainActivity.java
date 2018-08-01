package com.itshizhan.notificationtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }




    public void doClick(View view) {
        Intent intent = new Intent(this,NotificationActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,1,intent,0);

        NotificationManager manager=
                (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Notification notification =new NotificationCompat.Builder(this,"default")
                .setContentTitle("title")
                .setContentText("This is content text")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                .setContentIntent(pendingIntent)
                .setSound(Uri.fromFile(new File("/system/media/audio/ringtones/Bounce.ogg")))
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setAutoCancel(true)
                .build();
        if (manager != null) {
            manager.notify(1,notification);
        }
    }
}
