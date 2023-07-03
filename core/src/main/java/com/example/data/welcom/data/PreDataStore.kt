package com.example.data.welcom.data

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.emptyPreferences
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject


const val PREFERENCE_NAME = "sag_preference"

class PreDataStore @Inject constructor(context: Context) {
    private object PreferenceKeys {
        val onBoarding = preferencesKey<Boolean>("my_name")
        val token = preferencesKey<String>("token")
    }

    private val dataStore: DataStore<androidx.datastore.preferences.Preferences> = context.createDataStore(name = "sag-datastore")

    suspend fun saveBooleanToDataStore(bool: Boolean) {
        dataStore.edit { preference ->
            preference[PreferenceKeys.onBoarding] = bool
        }
    }

    suspend fun saveTokenToDataStore(token: String) {
        dataStore.edit { preference ->
            preference[PreferenceKeys.token] = token
        }
    }
    val readFromDataStore: Flow<Boolean> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preference ->
            val myName = preference[PreferenceKeys.onBoarding] ?: false
            myName
        }

    val readTokenFromDataStore: Flow<String> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preference ->
            val token = preference[PreferenceKeys.token] ?: ""
            token
        }

}