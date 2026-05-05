package com.example.myapplicationtestthree.extensions

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import com.example.myapplicationtestthree.AttendanceApplication

/**
 * Kotlin Extension Functions for Context.
 * These replace repetitive boilerplate throughout the app.
 */

/** Show a short Toast without needing to pass context explicitly */
fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

/** Show a long Toast */
fun Context.showLongToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

/**
 * Retrieve the app-wide SharedPreferences from any Context.
 * Delegates to the Application instance so there is always one source of truth.
 */
fun Context.getSharedPrefs(): SharedPreferences {
    return (applicationContext as AttendanceApplication).sharedPreferences
}

/** Convenience: read a String from SharedPreferences with a default */
fun Context.getPrefString(key: String, default: String = ""): String {
    return getSharedPrefs().getString(key, default) ?: default
}

/** Convenience: write a String to SharedPreferences */
fun Context.putPrefString(key: String, value: String) {
    getSharedPrefs().edit().putString(key, value).apply()
}

/** Convenience: read a Boolean from SharedPreferences */
fun Context.getPrefBoolean(key: String, default: Boolean = false): Boolean {
    return getSharedPrefs().getBoolean(key, default)
}

/** Convenience: write a Boolean to SharedPreferences */
fun Context.putPrefBoolean(key: String, value: Boolean) {
    getSharedPrefs().edit().putBoolean(key, value).apply()
}