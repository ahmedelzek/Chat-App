package com.mis.route.chatapp

import android.app.Application
import com.mis.route.chatapp.data.preferences.PreferencesProvider

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        PreferencesProvider.context = this
    }
}