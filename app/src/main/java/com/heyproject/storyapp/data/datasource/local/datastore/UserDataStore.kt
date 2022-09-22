package com.heyproject.storyapp.data.datasource.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.heyproject.storyapp.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
Written by Yayan Rahmat Wijaya on 9/23/2022 05:34
Github : https://github.com/yayanrw
 **/

private const val SETTING_PREFERENCES_NAME = "setting_preferences"

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = SETTING_PREFERENCES_NAME
)

class UserDataStore(private val dataStore: DataStore<Preferences>) {
    fun getUser(): Flow<User> {
        return dataStore.data.map { preferences ->
            User(
                userId = preferences[USERID_KEY],
                name = preferences[NAME_KEY],
                token = preferences[TOKEN_KEY],
                isLogin = preferences[STATE_KEY]
            )
        }
    }

    suspend fun saveUser(user: User): Boolean {
        dataStore.edit { preferences ->
            preferences[USERID_KEY] = user.userId.orEmpty()
            preferences[NAME_KEY] = user.name.orEmpty()
            preferences[TOKEN_KEY] = user.token.orEmpty()
            preferences[STATE_KEY] = user.isLogin ?: false
        }
        return true
    }

    suspend fun removeUser(): Boolean {
        dataStore.edit { preferences ->
            preferences[USERID_KEY] = ""
            preferences[NAME_KEY] = ""
            preferences[TOKEN_KEY] = ""
            preferences[STATE_KEY] = false
        }
        return true
    }

    companion object {
        private val USERID_KEY = stringPreferencesKey("userID")
        private val NAME_KEY = stringPreferencesKey("name")
        private val TOKEN_KEY = stringPreferencesKey("token")
        private val STATE_KEY = booleanPreferencesKey("state")
    }
}