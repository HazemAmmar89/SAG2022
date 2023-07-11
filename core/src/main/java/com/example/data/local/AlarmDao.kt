package com.example.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.alarm.AlarmItem
import com.example.data.reminder.ReminderModel

@Dao
interface AlarmDao {

    @Query("select * from AlarmItem order by id desc ")
    suspend fun getAllAlarm(): List<AlarmItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewAlarm(alarmItem: AlarmItem): Long

    @Delete
    suspend fun deleteAlarm(alarmItem: AlarmItem)
}