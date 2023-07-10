package com.example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.alarm.AlarmItem
import com.example.data.alarm.DateConverter
import com.example.data.alarm.LocalDateTimeConverter
import com.example.data.reminder.ReminderModel

@Database(entities = [AlarmItem::class, ReminderModel::class], version = 1, exportSchema = false)
@TypeConverters(LocalDateTimeConverter::class, DateConverter::class)
abstract class RoomDB : RoomDatabase() {


    abstract fun alarmDao(): AlarmDao

    abstract fun reminderDao(): ReminderDao


}