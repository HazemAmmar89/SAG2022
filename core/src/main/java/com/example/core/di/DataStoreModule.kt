package com.example.core.di

import android.content.Context
import com.example.data.welcom.data.OnBoardPref
import com.example.data.welcom.data.OnBoardPrefImpl
import com.example.data.welcom.data.PreDataStore
import com.example.data.whatsapp.WhatsappSendMessageImpl
import com.example.features.authentication.data.StoreData
import com.example.features.authentication.data.StoreDataImpl
import com.example.features.whatsapp.WhatsappSendMessage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule  {

    @Singleton
    @Provides
    fun provideApplicationContext(
        @ApplicationContext context: Context
    ) = context
    @Provides
    @Singleton
    fun provideWhatsappController(@ApplicationContext context: Context): WhatsappSendMessage {
        return WhatsappSendMessageImpl(context)
    }
    @Provides
    @Singleton
    fun dataStoreManager(@ApplicationContext appContext: Context): PreDataStore =
        PreDataStore(appContext)

    @Provides
    @Singleton
    fun provideOnBoardDataStore(@ApplicationContext context: Context): OnBoardPref {
        return OnBoardPrefImpl(dataStoreManager(context))
    }

    @Provides
    @Singleton
    fun provideTokenDataStore(@ApplicationContext context: Context): StoreData {
        return StoreDataImpl(dataStoreManager(context))
    }
}