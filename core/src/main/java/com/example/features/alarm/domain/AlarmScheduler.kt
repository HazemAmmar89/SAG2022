package com.example.features.alarm.domain

import com.example.data.alarm.AlarmItem

interface AlarmScheduler {
    fun schedule(item: AlarmItem)
    fun cancel(item: AlarmItem)
}