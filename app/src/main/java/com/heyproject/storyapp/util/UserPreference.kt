package com.heyproject.storyapp.util

import android.content.Context
import com.heyproject.storyapp.model.User

internal class UserPreference(context: Context) {
    companion object {
        private const val PREFS_NAME = "user_pref"
        private const val USER_ID = "user_id"
        private const val NAME = "name"
        private const val TOKEN = "token"
    }

    private val preference = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setUser(user: User) {
        val editor = preference.edit()
        editor.putString(USER_ID, user.userId)
        editor.putString(NAME, user.name)
        editor.putString(TOKEN, user.token)
        editor.apply()
    }

    fun getUser(): User {
        val user = User()
        user.userId = preference.getString(USER_ID, "")
        user.name = preference.getString(NAME, "")
        user.token = preference.getString(TOKEN, "")
        return user
    }
}