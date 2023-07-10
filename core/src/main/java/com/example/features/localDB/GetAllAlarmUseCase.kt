package com.example.features.localDB

import com.example.core.usecase.LocalUseCase
import com.example.data.alarm.AlarmItem
import com.example.data.local.AlarmDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllAlarmUseCase @Inject constructor(private val alarmDao: AlarmDao):LocalUseCase<List<AlarmItem>,Any>() {
    override fun executeLocalDS(body: Any?): Flow<List<AlarmItem>> =flow{
        emit(alarmDao.getAllAlarm())
    }
}