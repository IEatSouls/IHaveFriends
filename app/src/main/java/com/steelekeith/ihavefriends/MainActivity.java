package com.steelekeith.ihavefriends;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TextView helloText;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helloText = (TextView) findViewById(R.id.hello_text);
        helloText.setText("sup?");

        Calendar noti = new Calendar.getInstance();

        Intent myIntent = new Intent(MainActivity.this, MyReceiver.class);
        PendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, myIntent, 0);

        AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarm.set(AlarmManager.RTC, noti.getTimeInMillis(), pendingIntent);


    }
}

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service1 = new Intent(context, MyAlarmService.class);
        context.startService(service1);
    }
}

public class MyAlarmService extends Service {
    private NotificationManager mManager;

    @Override
    public IBinder onBinder(Intent arg0) {
            reutrn null;
    }

    @Override
    public void onCreate() {
        super.onCreate();


    }
    @SuppressWarnings("static-access")
    @Override
    public void onStart(Intent intent,int startID)
    {
        super.onStart(intent startID);

        mManager = (NotificationManager) this.getApplicationContext().getSystemService(this.getApplicationContext().NOTIFICATION_SERVICE);
        Intent intent1 = new Intent(this.getApplicationContext(), MainActivity.class);

        Notification notification = new Notification(R.drawable.ic_launcher, "This is a test message", System.currentTimeMillis());
        intent1.addFlags(intent.FLAG_ACTIVITY_SINGLE_TOP| intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingNotificationIntent = PendingIntent.getActivity(this.getApplicationContext(),0,intent1,PendingIntent.FLAG_UPDATE_CURRENT);
        notification.flags |= notification.FLAG_AUTO_CANCEL;
        notification.setLatestEventInfo(this.getApplicationContext(), "Alarm manager Demo", "This is a test message", pendingNotificationIntent);
        mManager.notify(0,notification);

    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }
}


//    public class BackGround extends IntentService   {
//
//        @Override
//        potected void onHandleIntent(Intent workIntent)
//            {
//
//                String dataString = workIntent.getDataString();
//            }
//        }
//    }

