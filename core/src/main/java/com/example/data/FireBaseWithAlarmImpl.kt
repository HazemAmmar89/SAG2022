package com.example.data

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.data.alarm.AlarmItem
import com.example.data.reminder.ReminderModel
import com.example.data.reminder.enums.ReminderRepeatTypes
import com.example.features.alarm.domain.AlarmScheduler
import com.example.features.firebase.FireBaseWithAlarm
import com.example.features.reminder.ReminderScheduler
import com.example.features.whatsapp.WhatsappSendMessage
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.time.LocalDateTime
import java.util.Calendar
import javax.inject.Inject

class FireBaseWithAlarmImpl @Inject constructor(
    private val alarmScheduler: AlarmScheduler,
    private val reminderScheduler: ReminderScheduler,
    private val whatsapp: WhatsappSendMessage
) :
    FireBaseWithAlarm {
    private val sagRef = FirebaseDatabase.getInstance().getReference("commands")

    override suspend fun alarmOperations() {
//        val calendar=Calendar.getInstance()
//        calendar[Calendar.YEAR] = 2023
//        calendar[Calendar.MONTH] = Calendar.JUNE
//        calendar[Calendar.DAY_OF_MONTH] = 30
//        calendar[Calendar.HOUR_OF_DAY] = 11
//        calendar[Calendar.AM_PM]=Calendar.PM
//        calendar[Calendar.MINUTE] = 59
//        calendar[Calendar.MILLISECOND] = 0
//        calendar[Calendar.SECOND] = 0
//        Log.e("remainder22", "alarmOperations:${calendar.time} ____ ${calendar.timeInMillis}", )
//        val reminderItem=ReminderModel(id = 1, title = "hazem",description = "hello it time to done this task", repeat = ReminderRepeatTypes.DAILY,isDone = false, date = calendar)
//        reminderItem.let (reminderScheduler::scheduleReminder)

        val alarmRef = sagRef.child("alarm")
        val schedulerRef = alarmRef.child("scheduler")
        val cancelRef = alarmRef.child("cancel")
        schedulerRef.addValueEventListener(object : ValueEventListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach {
                    val collectPhoneNumbers = (it.value as Map<*, *>).toMutableMap()
                    val seconds = collectPhoneNumbers["seconds"]
                    if (collectPhoneNumbers["success"].toString() == "0") {
                        scheduleAlarm(
                            AlarmItem(
                                time = LocalDateTime.now()
                                    .plusSeconds(seconds.toString().toLong()),
                                it.key.toString()
                            )
                        )
                        collectPhoneNumbers["success"] = 1
                        schedulerRef.child(it.key.toString()).setValue(collectPhoneNumbers)
                    }

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("firebasesched", "onDataChange: ${error.message}")
            }

        })

        cancelRef.addValueEventListener(object : ValueEventListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach {
                    Log.e("snapshotcancel", "onDataChange: ${it.value}")
                    val cancelCollector = (it.value as Map<*, *>).toMutableMap()
                    if (cancelCollector["success"].toString() == "0") {
                        cancelCollector["success"] = 1
                        cancelRef.child(it.key.toString()).setValue(cancelCollector)

                    }


                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("firebasesched", "onDataChange: ${error.message}")
            }

        })


    }

    override suspend fun whatsappMessage() {
        val whatsappRef = sagRef.child("whatsapp")
        whatsappRef.addValueEventListener(object : ValueEventListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.e("whatsappSnapshot", "onDataChange: $snapshot")
                snapshot.children.forEach { phoneNumber ->
                    val collectPhoneNumbers = (phoneNumber.value as Map<*, *>).toMutableMap()
                    val message = collectPhoneNumbers["message"]
                    Log.e("ll", "onDataChange: ${message.toString()}")
                    if (collectPhoneNumbers["success"].toString() == "0") {

                        whatsappSendMessage(
                            phoneNumber.key.toString(),
                            message = message.toString()
                        )


                        collectPhoneNumbers["success"] = 1
                        whatsappRef.child(phoneNumber.key.toString()).setValue(collectPhoneNumbers)
                    }


                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("firebasesched", "onDataChange: ${error.message}")
            }

        })

    }

    private fun scheduleAlarm(alarmItem: AlarmItem) {
        alarmItem.let(alarmScheduler::schedule)

    }

    private fun cancelAlarm(alarmItem: AlarmItem) {
        alarmItem.let(alarmScheduler::cancel)

    }

    private fun whatsappSendMessage(phone: String, message: String) {

        try {
            whatsapp.shareText(phone, message)
        } catch (e: Exception) {
            Log.e("exceptionwhat", "onDataChange: ${e.localizedMessage}")
        }

    }
}
