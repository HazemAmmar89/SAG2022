package com.example.sagapp.commands.data.remote

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.data.alarm.AlarmItem
import com.example.features.alarm.domain.AlarmScheduler
import com.example.features.firebase.FireBaseWithAlarm
import com.example.sagapp.android.Action
import com.example.sagapp.android.BaseViewModel
import com.example.sagapp.android.extentions.showLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class FireBaseCommand : Action {
    data class ReadCommand(val message: String) : FireBaseCommand()
    object Cancel : FireBaseCommand()
    object Schedule : FireBaseCommand()
}

@HiltViewModel
class CommandsViewModel @Inject constructor(private val alarmScheduler: FireBaseWithAlarm) :
    BaseViewModel<FireBaseCommand>() {


    fun readMessagesFromFirebase() {
        viewModelScope.launch {
            alarmScheduler.alarmOperations()
            Log.d("llllllllllll", "readMessagesFromFirebase: done")
        }
    }
    fun whatsappMessageFromFirebase() {
        viewModelScope.launch {
            alarmScheduler.whatsappMessage()
            Log.d("llllllllllll", "readMessagesFromFirebase: done")
        }
    }

    fun reminderMessageFromFirebase() {
        viewModelScope.launch {
            alarmScheduler.reminderOperations()
            Log.d("llllllllllll", "readMessagesFromFirebase: done")
        }
    }
}
