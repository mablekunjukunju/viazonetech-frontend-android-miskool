package com.zone.android.miskool_Util;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.util.Log;

import com.zone.android.miskool.R;
import com.zone.android.miskool_View.mainViewClass;

/**
 * Created by Inspiron on 12-03-2018.
 */

public class Utils {

    public static NotificationManager mManager;



  /*  public static void generateNotification(Context context) {



        android.support.v7.app.NotificationCompat.Builder nb = new android.support.v7.app.NotificationCompat.Builder(context);
        nb.setSmallIcon(R.drawable.alert);
        nb.setContentTitle("Brother , Have your read hadish today ?");
        nb.setContentText("Hadish can makes our life Enlightened");
        nb.setTicker("Take a look");

        nb.setAutoCancel(true);


        //get the bitmap to show in notification bar
        Bitmap bitmap_image = BitmapFactory.decodeResource(context.getResources(), R.drawable.alertb);
        android.support.v7.app.NotificationCompat.BigPictureStyle s = new android.support.v7.app.NotificationCompat.BigPictureStyle().bigPicture(bitmap_image);
        s.setSummaryText("Hadish can makes our life Enlightened");
        nb.setStyle(s);


        Intent resultIntent = new Intent(context, mainViewClass.class);
        TaskStackBuilder TSB = TaskStackBuilder.create(context);
        TSB.addParentStack(mainViewClass.class);
        // Adds the Intent that starts the Activity to the top of the stack
        TSB.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                TSB.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        nb.setContentIntent(resultPendingIntent);
        nb.setAutoCancel(true);
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(11221, nb.build());
    }*/
}