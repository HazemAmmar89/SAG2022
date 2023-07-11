package com.example.data.reminder

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.data.alarm.DateConverter
import com.example.data.reminder.enums.ReminderRepeatTypes
import kotlinx.parcelize.Parcelize
import java.util.*

@Entity
@Parcelize
data class ReminderModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val description: String? = null,
    val repeat: ReminderRepeatTypes = ReminderRepeatTypes.ONCE,
    @TypeConverters(DateConverter::class)
    val date: Calendar,
) : Parcelable
