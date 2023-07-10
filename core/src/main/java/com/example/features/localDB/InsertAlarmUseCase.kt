package com.example.features.localDB

import com.example.core.usecase.LocalUseCase
import com.example.data.alarm.AlarmItem
import com.example.data.local.AlarmDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertAlarmUseCase @Inject constructor(private val alarmDao: AlarmDao):
    LocalUseCase<Long, AlarmItem>() {
    override fun executeLocalDS(body: AlarmItem?): Flow<Long> =flow{
       emit( alarmDao.insertNewAlarm(body!!))
    }
}