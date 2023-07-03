package com.example.features.authentication.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface StoreData {
    val isStored: StateFlow<String>

    suspend fun onTokenWrite(token:String)
    fun onTokenRead(): Flow<String>
}