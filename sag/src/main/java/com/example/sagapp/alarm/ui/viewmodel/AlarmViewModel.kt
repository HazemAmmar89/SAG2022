package com.example.sagapp.alarm.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.core.response.Resource
import com.example.data.alarm.AlarmItem
import com.example.features.alarm.domain.AlarmScheduler
import com.example.features.localDB.GetAllAlarmUseCase
import com.example.features.localDB.InsertAlarmUseCase
import com.example.sagapp.android.Action
import com.example.sagapp.android.BaseViewModel
import com.example.sagapp.android.extentions.showLog
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

sealed class AlarmActions:Action{
    object Cancel:AlarmActions()
    object  Schedule:AlarmActions()

    data class Success(val success:Long):AlarmActions()
    data class AllAlarm(val success:List<AlarmItem>):AlarmActions()
}
@HiltViewModel
class AlarmViewModel @Inject constructor(private val alarmScheduler: AlarmScheduler,private val insertAlarmUseCase: InsertAlarmUseCase,private val getAlarmUseCase: GetAllAlarmUseCase
) : BaseViewModel<AlarmActions>() {
    fun scheduleAlarm(alarmItem: AlarmItem){
        insertAlarmUseCase(viewModelScope,alarmItem){
            when(it){
                is Resource.Failure -> {

                }
                is Resource.Progress -> {

                }
                is Resource.Success -> {
                    produce(AlarmActions.Success(it.data))
                }
            }
        }
          alarmItem.let (alarmScheduler::schedule)
           produce(AlarmActions.Schedule)
    }
    fun getAllAlarm(){
        getAlarmUseCase(viewModelScope){
            when(it){
                is Resource.Failure -> {

                }
                is Resource.Progress -> {

                }
                is Resource.Success -> {
                    it.data.showLog("hazemamaar")
                    produce(AlarmActions.AllAlarm(it.data))
                }
            }
        }
    }
    fun cancelAlarm(alarmItem: AlarmItem){
        alarmItem.let (alarmScheduler::cancel)
        produce(AlarmActions.Cancel)
    }
}