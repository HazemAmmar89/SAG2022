package com.example.sagapp.commands.domain

import android.annotation.SuppressLint
import android.app.Notification
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri
import com.example.core.R
import com.example.sagapp.screens.ui.activities.ScreensActivity
import timber.log.Timber

class AlarmReceiver: BroadcastReceiver() {
    lateinit var mPlayer: MediaPlayer
    @SuppressLint("MissingPermission")
    override fun onReceive(context: Context?, intent: Intent?) {
        val message = intent?.getIntExtra("EXTRA_MESSAGE",3) ?: return
        Log.e("intentmessage", "onReceive:$message ")

            if (message ==1) {
                Log.e("hazemaa", "onReceive: time to wake up"+message )

                // Set the alarm here.
                mPlayer =MediaPlayer.create(context, R.raw.alarm)
                try {
                    mPlayer.prepare()
                }catch (e:java.lang.Exception) {

                    Timber.tag("hazemaa").e(e.localizedMessage?.toString())
                }
                mPlayer.start()
            }else{
                Log.e("hazemaa", "onReceive: time to cancle"+message )
            }
//        val notificationSound: Uri =
//            RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
//
//        val reminderDetailIntent = Intent(
//            Intent.ACTION_VIEW,
//            "https://example.com/reminderId=1".toUri(),
//            context,
//            ScreensActivity::class.java
//        )
//
//        val pending: PendingIntent = TaskStackBuilder.create(context).run {
//            addNextIntentWithParentStack(reminderDetailIntent)
//            getPendingIntent(123, PendingIntent.FLAG_UPDATE_CURRENT)
//        }
//
//        val notification = NotificationCompat.Builder(context!!, "reminder")
//            .setSmallIcon(R.drawable.second_onboard)
//            .setContentTitle("sag")
//            .setContentText("notifie")
//            .setAutoCancel(true)
//            .setSound(notificationSound)
//            .setDefaults(Notification.DEFAULT_ALL)
//            .setContentIntent(pending)
//
//        val notificationManager = NotificationManagerCompat.from(context)
//
//        notificationManager.notify(
//            123,
//            notification.build()
//        )

//        val message = intent?.getStringExtra("EXTRA_MESSAGE") ?: return
//        println("Alarm triggered: $message")
    }
}