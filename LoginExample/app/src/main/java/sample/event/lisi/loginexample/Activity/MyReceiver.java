package sample.event.lisi.loginexample.Activity;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import sample.event.lisi.loginexample.R;

/**
 * Created by Administrator on 2015/12/25.
 */
public class MyReceiver extends BroadcastReceiver {


    NotificationManager notificationManager;
    public static final String NOTIFICATION_TITLE = "NOTIFICATION_TITLE";
    public static final String NOTIFICATION_CONTENT ="NOTIFICATION_CONTENT";
    private static final int NOTIFICATION_ID = 1;
    @Override
    public void onReceive(Context context, Intent intent) {
        notificationManager = (NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.drawable.chimu)
                .setContentTitle(intent.getStringExtra(NOTIFICATION_TITLE))
                .setContentText(intent.getStringExtra(NOTIFICATION_CONTENT))
                .setProgress(0,0,true);
        notificationManager.notify(NOTIFICATION_ID,builder.build());


    }
}
