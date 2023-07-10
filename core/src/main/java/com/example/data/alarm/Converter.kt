package com.example.data.alarm

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Date
import java.util.GregorianCalendar


object LocalDateTimeConverter {
    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun toDate(dateString: String?): LocalDateTime? {
        return if (dateString == null) {
            null
        } else {
            LocalDateTime.parse(dateString.toString())
        }
    }

    @TypeConverter
    fun toDateString(date: LocalDateTime?): String? {
        return date?.toString()
    }
}

object DateConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Calendar? {

        if(value == null) return null

        val cal = GregorianCalendar()
        cal.timeInMillis = value
        return cal
    }

    @TypeConverter
    fun toTimestamp(timestamp: Calendar?): Long? {

        if(timestamp == null) return null

        return timestamp.timeInMillis
    }
}