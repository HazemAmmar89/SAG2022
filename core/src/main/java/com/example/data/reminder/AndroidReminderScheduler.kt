package com.example.data.reminder

import android.app.AlarmManager
import android.app.Notification
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri
import com.example.core.R
import com.example.data.reminder.enums.ReminderRepeatTypes
import com.example.features.reminder.ReminderScheduler
import java.time.ZoneId
import javax.inject.Inject

class AndroidReminderScheduler @Inject constructor(private val context: Context
): ReminderScheduler {
    private val alarmManager = context.getSystemService(AlarmManager::class.java)
    private var intent = Intent(context, ReminderAlarmReceiver::class.java)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun scheduleReminder(item: ReminderModel) {
        Log.e("hhaze", "scheduleReminder: ${item.date}", )
        intent.apply {
            intent.putExtra("title", item.title)
            intent.putExtra("description", item.description)
        }
        val interval = when (item.repeat) {
            ReminderRepeatTypes.ONCE -> 0
            ReminderRepeatTypes.DAILY -> AlarmManager.INTERVAL_DAY
            ReminderRepeatTypes.WEEKLY -> AlarmManager.INTERVAL_DAY * 7
            ReminderRepeatTypes.MONTHLY -> Constants.MONTH_IN_MILLISECONDS
            ReminderRepeatTypes.YEARLY -> Constants.YEAR_IN_MILLISECONDS
            else -> null
        }
        val pendingIntent =
            PendingIntent.getBroadcast(context, item.id.hashCode(), intent,PendingIntent.FLAG_IMMUTABLE
            )
        interval?.let {
            if (item.repeat != ReminderRepeatTypes.ONCE)
                alarmManager.setInexactRepeating(
                    AlarmManager.RTC_WAKEUP, item.date.timeInMillis,
                    it, pendingIntent
                )
            else alarmManager.set(
                AlarmManager.RTC_WAKEUP,
                item.date.timeInMillis,
                pendingIntent
            )
        }
    }

    override fun cancelReminder(item: ReminderModel) {
        TODO("Not yet implemented")
    }
}