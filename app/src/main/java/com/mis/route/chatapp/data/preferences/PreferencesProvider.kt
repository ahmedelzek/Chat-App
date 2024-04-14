package com.mis.route.chatapp.data.preferences

import android.app.Application
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

class PreferencesProvider {
    companion object {
        lateinit var context: Application
        const val LOGGED_IN_KEY = "logged_key"
    }

    private val prefs: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(context)

    fun loggedIn(isLoggedIn: Boolean) {
        prefs.edit().putBoolean(LOGGED_IN_KEY, isLoggedIn).apply()
    }
}