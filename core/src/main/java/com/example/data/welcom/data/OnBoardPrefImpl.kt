package com.example.data.welcom.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class OnBoardPrefImpl @Inject constructor(private val prefDataStore: PreDataStore) :
    OnBoardPref {
    private val _isWatched = MutableStateFlow(false)
    override val isWatched: StateFlow<Boolean>
        get() = _isWatched.asStateFlow()
    override suspend fun onBoardWriteToFinish() {
        prefDataStore.saveBooleanToDataStore(true)
    }

    override  fun onBoardReadToFinish() : Flow<Boolean> =
        prefDataStore.readFromDataStore

}