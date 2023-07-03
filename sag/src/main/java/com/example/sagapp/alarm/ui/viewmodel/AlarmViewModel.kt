package com.example.sagapp.alarm.ui.viewmodel

import com.example.data.alarm.AlarmItem
import com.example.features.alarm.domain.AlarmScheduler
import com.example.sagapp.android.Action
import com.example.sagapp.android.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

sealed class AlarmActions:Action{
    object Cancel:AlarmActions()
    object  Schedule:AlarmActions()
}
@HiltViewModel
class AlarmViewModel @Inject constructor(private val alarmScheduler: AlarmScheduler) : BaseViewModel<AlarmActions>() {
    fun scheduleAlarm(alarmItem: AlarmItem){
          alarmItem.let (alarmScheduler::schedule)
           produce(AlarmActions.Schedule)
    }
    fun cancelAlarm(alarmItem: AlarmItem){
        alarmItem.let (alarmScheduler::cancel)
        produce(AlarmActions.Cancel)
    }
}