package com.example.features.authentication.data

import com.example.data.welcom.data.PreDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class StoreDataImpl@Inject constructor(private val prefDataStore: PreDataStore) :
    StoreData {
    private val _isWatched = MutableStateFlow("")
    override val isStored: StateFlow<String>
        get() = _isWatched.asStateFlow()

    override fun onTokenRead(): Flow<String> {
        return prefDataStore.readTokenFromDataStore
    }

    override suspend fun onTokenWrite(token:String) {
        prefDataStore.saveTokenToDataStore(token)
    }

    }
