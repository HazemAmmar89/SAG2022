package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.alarm.AlarmItem
import com.example.data.reminder.ReminderModel

@Dao
interface ReminderDao {
    @Query("select * from ReminderModel order by date desc ")
    suspend fun getAllReminders(): List<ReminderModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewReminder(rem: ReminderModel): Long

}