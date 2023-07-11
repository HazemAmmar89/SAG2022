package com.example.sagapp.alarm.ui

import android.content.Context
import com.example.data.FireBaseWithAlarmImpl
import com.example.sagapp.commands.domain.AndroidAlarmScheduler
import com.example.data.reminder.AndroidReminderScheduler
import com.example.features.alarm.domain.AlarmScheduler
import com.example.features.firebase.FireBaseWithAlarm
import com.example.features.localDB.InsertAlarmUseCase
import com.example.features.reminder.ReminderScheduler
import com.example.features.whatsapp.WhatsappSendMessage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AlarmModule {
    @Provides
    @Singleton
    fun provideAlarmController(@ApplicationContext context: Context): AlarmScheduler {
        return AndroidAlarmScheduler(context)
    }
    @Provides
    @Singleton
    fun provideReminderController(@ApplicationContext context: Context): ReminderScheduler {
        return AndroidReminderScheduler(context)
    }
    @Provides
    fun provideRepository( alarmScheduler: AlarmScheduler,reminderScheduler: ReminderScheduler,whatsappSendMessage: WhatsappSendMessage,insertAlarmUseCase: InsertAlarmUseCase): FireBaseWithAlarm =
        FireBaseWithAlarmImpl(alarmScheduler,reminderScheduler,whatsappSendMessage,insertAlarmUseCase)
}