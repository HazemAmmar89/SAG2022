package com.example.data.welcom.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface OnBoardPref {
    val isWatched: StateFlow<Boolean>

    suspend fun onBoardWriteToFinish()
    fun onBoardReadToFinish(): Flow<Boolean>
}