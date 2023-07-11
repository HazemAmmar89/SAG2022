package com.example.data.alarm

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime

@Entity
@kotlinx.parcelize.Parcelize
data class AlarmItem(
    @PrimaryKey(autoGenerate = true)
    val id :Int?=null,
    val time: LocalDateTime,
    val message: String
) : Parcelable
