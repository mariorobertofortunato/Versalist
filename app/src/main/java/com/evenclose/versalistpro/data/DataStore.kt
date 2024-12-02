package com.evenclose.versalistpro.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStore(private val context: Context) {

    companion object {
        private val Context.dataStore by preferencesDataStore("settings")
        private val LANGUAGE = stringPreferencesKey("language")
    }

    val getLanguage: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[LANGUAGE] ?: ""
    }

    suspend fun saveLanguage(language: String) {
        context.dataStore.edit { preferences ->
            preferences[LANGUAGE] = language
        }
    }
}