package com.mohylov.diet.ui.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlinx.coroutines.flow.first
import javax.inject.Inject

interface Persistence {

    suspend fun getString(key: String, defaultValue: String = ""): String
    suspend fun putString(value: String, key: String)

    suspend fun getInt(key: String, defaultValue: Int = 0): String
    suspend fun putInt(value: Int, key: String)

    suspend fun getBoolean(key: Boolean, defaultValue: Boolean): String
    suspend fun putBoolean(value: Boolean, key: String)

    suspend fun putSet(key: String, setValue: Set<String>)
    suspend fun getSet(key: String, defaultValue: Set<String> = emptySet())

    suspend fun contains(key: String): Boolean

    suspend fun clear()
}

class DataStorePersistence @Inject constructor(private val dataStore: DataStore<Preferences>) :
    Persistence {

    override suspend fun getString(key: String, defaultValue: String): String {
        return dataStore.data.first()[stringPreferencesKey(key)] ?: defaultValue
    }


    override suspend fun putString(value: String, key: String) {
        dataStore.edit { it[stringPreferencesKey(key)] = value }
    }

    override suspend fun getInt(key: String, defaultValue: Int): String {
        TODO("Not yet implemented")
    }

    override suspend fun putInt(value: Int, key: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getBoolean(key: Boolean, defaultValue: Boolean): String {
        TODO("Not yet implemented")
    }

    override suspend fun putBoolean(value: Boolean, key: String) {
        TODO("Not yet implemented")
    }

    override suspend fun putSet(key: String, setValue: Set<String>) {
        dataStore.edit { it[stringSetPreferencesKey(key)] = setValue }
    }

    override suspend fun getSet(key: String, defaultValue: Set<String>) {
        TODO("Not yet implemented")
    }

    override suspend fun contains(key: String): Boolean {
        return dataStore.data.first().contains(stringSetPreferencesKey(key))
    }

    override suspend fun clear() {
        dataStore.edit { it.clear() }
    }

}