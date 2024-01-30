package com.example.ass1_lucntph42288;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.ass1_lucntph42288.Notification.NotifyConfig;

import java.util.Date;

public class NotificationUtils {

    @SuppressLint("MissingPermission")
    public static void GuiThongBao(Context context, String title, String content) {
        Intent intentDemo = new Intent(context, TasksActivity.class);
        intentDemo.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intentDemo.putExtra("duLieu", "Nội dung gửi từ Notify của main vào activity  msg .... ");

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntentWithParentStack(intentDemo);

        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        Bitmap anh = BitmapFactory.decodeResource(context.getResources(), R.drawable.img);

        Notification customNotification = new NotificationCompat.Builder(
                context, NotifyConfig.CHANEL_ID)
                .setSmallIcon(android.R.drawable.ic_delete)
                .setContentTitle(title)
                .setContentText(content)
                .setContentIntent(resultPendingIntent)
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(anh).bigLargeIcon(null))
                .setColor(Color.BLUE)
                .setAutoCancel(true)
                .build();

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);

        int id_notify = (int) new Date().getTime();
        notificationManagerCompat.notify(id_notify, customNotification);
    }
}

