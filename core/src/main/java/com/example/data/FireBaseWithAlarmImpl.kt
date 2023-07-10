package com.example.data

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.data.alarm.AlarmItem
import com.example.data.reminder.ReminderModel
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
    private val whatsapp: WhatsappSendMessage,
) :
    FireBaseWithAlarm {
    private val sagRef = FirebaseDatabase.getInstance().getReference("commands")

    override suspend fun alarmOperations() {


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
                                message = it.key.toString()
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

    override suspend fun reminderOperations() {
        val reminderRef = sagRef.child("reminder")
        val calendar = Calendar.getInstance()
        reminderRef.addValueEventListener(object : ValueEventListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.e("whatsappSnapshot", "onDataChange: $snapshot")
                snapshot.children.forEach { phoneNumber ->
                    val collectPhoneNumbers = (phoneNumber.value as Map<*, *>).toMutableMap()
                    Log.e("llreminder", "onDataChange: ${collectPhoneNumbers.toString()}")
                    if (collectPhoneNumbers["success"].toString() == "0") {
                        Log.e("reminder____", "onDataChange: ${collectPhoneNumbers.toString()}")
                        calendar[Calendar.YEAR] = collectPhoneNumbers["year"].toString().toInt()
                        calendar[Calendar.MONTH] =
                            collectPhoneNumbers["month"].toString().toInt() - 1
                        calendar[Calendar.DAY_OF_MONTH] =
                            collectPhoneNumbers["day"].toString().toInt()
                        calendar[Calendar.HOUR_OF_DAY] =
                            collectPhoneNumbers["hour"].toString().toInt()
                        calendar[Calendar.AM_PM] = collectPhoneNumbers["AM_PM"].toString().toInt()
                        calendar[Calendar.MINUTE] = collectPhoneNumbers["minute"].toString().toInt()
                        calendar[Calendar.MILLISECOND] = 0
                        calendar[Calendar.SECOND] = 0
                        val reminderItem = ReminderModel(
                            title = collectPhoneNumbers["title"].toString(),
                            description = collectPhoneNumbers["description"].toString(),
                            date = calendar
                        )
                        reminderItem.let(reminderScheduler::scheduleReminder)
                        collectPhoneNumbers["success"] = 1
                        reminderRef.child(phoneNumber.key.toString()).setValue(collectPhoneNumbers)


                    }


                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("firebasesched", "onDataChange: ${error.message}")
            }

        })


        Log.e("remainder22", "alarmOperations:${calendar.time} ____ ${calendar.timeInMillis}")

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
