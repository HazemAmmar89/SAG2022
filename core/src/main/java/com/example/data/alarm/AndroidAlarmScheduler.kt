package com.example.data.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.features.alarm.domain.AlarmScheduler

import java.time.ZoneId
import javax.inject.Inject

class AndroidAlarmScheduler @Inject constructor(
    private val context: Context
): AlarmScheduler {

    private val alarmManager = context.getSystemService(AlarmManager::class.java)
    private var intent = Intent(context, AlarmReceiver::class.java)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun schedule(item: AlarmItem) {
       intent.apply {
           putExtra("EXTRA_MESSAGE", 1)
       }
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            item.time.atZone(ZoneId.systemDefault()).toEpochSecond() * 1000,
            PendingIntent.getBroadcast(
                context,
                item.message.hashCode(),
                intent,
                PendingIntent.FLAG_IMMUTABLE
            )

        )
    }

    override fun cancel(item: AlarmItem) {
        Log.e("a7a7a", "cancel: ${item.message}", )
        intent.apply {
            putExtra("EXTRA_MESSAGE", 0)
        }
        alarmManager.cancel(
            PendingIntent.getBroadcast(
                context,
                item.message.hashCode(),
                intent,
                PendingIntent.FLAG_IMMUTABLE
            )
        )
    }


}