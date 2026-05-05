package com.example.myapplicationtestthree

import android.app.Application
import android.content.SharedPreferences
import com.example.myapplicationtestthree.data.repository.UserRepository

/**
 * Custom Application class for Attendly.
 * Serves as the single entry point for app-wide initialization.
 * Registered in AndroidManifest.xml via android:name=".AttendanceApplication"
 */
class AttendanceApplication : Application() {

    // Lazily initialized SharedPreferences — one instance shared across the app
    val sharedPreferences: SharedPreferences by lazy {
        getSharedPreferences("UserPrefs", MODE_PRIVATE)
    }

    // Lazily initialized UserRepository — depends on sharedPreferences above
    val userRepository: UserRepository by lazy {
        UserRepository(sharedPreferences)
    }

    override fun onCreate() {
        super.onCreate()
        // Future: initialize analytics, crash reporting, Timber logging, etc.
    }

    companion object {
        const val PREFS_NAME = "UserPrefs"
    }
}