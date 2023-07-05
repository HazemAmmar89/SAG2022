package com.example.data.reminder

import android.Manifest
import android.app.Notification
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.RingtoneManager
import android.net.Uri
import android.util.Log
import androidx.core.app.ActivityCompat

import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri
import com.example.core.R

import kotlin.random.Random

class ReminderAlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        intent!!.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_TASK_ON_HOME
        Log.e("hazooom", "onReceive: wake up reminder", )
        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")
        val id = intent.getIntExtra("id", Random.nextInt())

//        val notificationSound: Uri =
//            RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
//
//        val reminderDetailIntent = Intent(
//            Intent.ACTION_VIEW,
//            "https://example.com/reminderId=$id".toUri(),
//            context,
//            ReminderAlarmReceiver::class.java
//        )
//
//        val pending: PendingIntent = TaskStackBuilder.create(context).run {
//            addNextIntentWithParentStack(reminderDetailIntent)
//            getPendingIntent(id, PendingIntent.FLAG_UPDATE_CURRENT)
//        }
//
//        val notification = NotificationCompat.Builder(context!!, "reminder")
//            .setSmallIcon(R.drawable.first_onboarding)
//            .setContentTitle(title)
//            .setContentText(description)
//            .setAutoCancel(true)
//            .setSound(notificationSound)
//            .setDefaults(Notification.DEFAULT_ALL)
//            .setContentIntent(pending)
//
//        val notificationManager = NotificationManagerCompat.from(context)
//
//        if (ActivityCompat.checkSelfPermission(
//                context,
//                Manifest.permission.POST_NOTIFICATIONS
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return
//        }
//        notificationManager.notify(
//            id,
//            notification.build()
//        )
//    }
}}