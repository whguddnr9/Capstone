package com.caps.exear;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn_change;
    private Button btn_device;
    public static final String NOTI_CH_ID = "183911";
    public static final String NOTI_CH_NAME = "blueroller";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_change=findViewById(R.id.btn_change);
        btn_device=findViewById(R.id.btn_device);


        btn_change.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, ChangeActivity.class);

                startActivity(intent1);
            }

        });

        findViewById(R.id.btn_device).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(MainActivity.this, DeviceActivity.class);

                startActivity(intent2);
            }


    });

        final Button btn_push = (Button) findViewById(R.id.btn_push);
        btn_push.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        connNotification();
                }
        });
    }

    public void connNotification(){

        NotificationManager notificationManager
                = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Bitmap mLargeIconForNoti
                =BitmapFactory.decodeResource(getResources(), R.drawable.large_icon);

        PendingIntent mPendingIntent
                = PendingIntent.getActivity(MainActivity.this, 0,
                new Intent(getApplicationContext(), MainActivity.class),
                PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationCompat.Builder noti = new NotificationCompat.Builder(this, NOTI_CH_ID)
                .setSmallIcon(R.drawable.small_icon)
                .setColor(ContextCompat.getColor(MainActivity.this, R.color.colorAccent))
                .setContentTitle("BLUEROLLER")
                .setContentText("??????????????? ????????? ??????????????????.")
                .setDefaults(Notification.DEFAULT_SOUND)//?????? ??????/??????
                .setLargeIcon(mLargeIconForNoti)//???????????? ??? ?????????
                .setPriority(NotificationCompat.PRIORITY_HIGH)//?????????
                .setAutoCancel(true)//????????? ???????????? ??? true??? ????????????, false??? ??????
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)//???????????? ??? ?????? ?????? ??????
                .setContentIntent(mPendingIntent);//??????????????????

        //?????? ??????
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel
                    = new NotificationChannel(NOTI_CH_ID, NOTI_CH_NAME, NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription("????????????????????????");

            assert notificationManager != null;
            notificationManager.createNotificationChannel(notificationChannel);
        }else noti.setSmallIcon(R.mipmap.small_icon); // Oreo ???????????? mipmap ???????????? ????????? Couldn't create icon: StatusBarIcon ?????????

        assert notificationManager != null;
        notificationManager.notify(1234, noti.build()); // ??????????????? ?????????????????? ????????????

    }

}