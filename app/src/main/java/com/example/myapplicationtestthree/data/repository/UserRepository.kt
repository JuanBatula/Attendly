package com.example.myapplicationtestthree.data.repository

import android.content.SharedPreferences

/**
 * UserRepository — the Model layer in MVP.
 *
 * All SharedPreferences read/write operations for user data live here.
 * Presenters call this class; Activities and Fragments never touch
 * SharedPreferences directly.
 */
class UserRepository(private val prefs: SharedPreferences) {

    companion object {
        private const val KEY_USER_NAME = "USER_NAME"
        private const val KEY_IS_LOGGED_IN = "isLoggedIn"
    }

    /** Save the user's name and mark them as logged in */
    fun saveUser(username: String) {
        prefs.edit()
            .putString(KEY_USER_NAME, username)
            .putBoolean(KEY_IS_LOGGED_IN, true)
            .apply()
    }

    /** Retrieve the stored username, defaulting to "User" */
    fun getUsername(): String {
        return prefs.getString(KEY_USER_NAME, "User") ?: "User"
    }

    /** Check whether a user is currently logged in */
    fun isLoggedIn(): Boolean {
        return prefs.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    /** Clear all stored data — used on logout */
    fun clearUser() {
        prefs.edit().clear().apply()
    }
}