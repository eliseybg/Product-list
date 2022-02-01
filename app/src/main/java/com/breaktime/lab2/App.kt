package com.breaktime.lab2

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.breaktime.lab2.util.Preferences
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {
    @Inject
    lateinit var preferences: Preferences

    override fun onCreate() {
        super.onCreate()
        if (preferences.getNightMode()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}