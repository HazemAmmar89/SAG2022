package com.example.features.reminder

import com.example.data.reminder.ReminderModel

interface ReminderScheduler {

    fun scheduleReminder(item: ReminderModel)
    fun cancelReminder(item: ReminderModel)
}