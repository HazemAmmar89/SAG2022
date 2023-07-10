package com.example.core.di

import android.content.Context
import androidx.room.Room
import com.example.data.local.RoomDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): RoomDB {
        return Room.databaseBuilder(
            appContext,
            RoomDB::class.java,
            "room_db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideAlarmDao(roomDataBase: RoomDB)= roomDataBase.alarmDao()


}