package com.minneydev.movieapp.manager

import android.content.Context
import com.minneydev.movieapp.data.User

class UserDataManager(private val context: Context) {

    private val USER_PREF = "user"
    private val EMAIL = "email"
    private val PASS = "password"
    private val LOGGED_IN = "isLoggedIn"

    fun saveUserData(email: String, password: String, isLoggedIn: Boolean) {
        context.getSharedPreferences(USER_PREF,Context.MODE_PRIVATE).edit()
            .putString(EMAIL, email)
            .putString(PASS, password)
            .putBoolean(LOGGED_IN, isLoggedIn)
            .apply()
    }

    fun readUserData(): User? {
        val contents = context.getSharedPreferences(USER_PREF,Context.MODE_PRIVATE).all
        return contents[LOGGED_IN]?.let {
            User(email = contents[EMAIL].toString(),
                password = contents[PASS].toString(),
                isLoggedIn = it as Boolean
            )
        }
    }

    fun readUserEmail(): String? {
        return context.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE)
            .getString(EMAIL, "")
    }

    fun readUserPassword(): String? {
        return context.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE)
            .getString(PASS, "")
    }

    fun readIsLoggedIn(): Boolean? {
        return context.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE)
            .getBoolean(LOGGED_IN, false)
    }

    fun logOutUser() {
        context.getSharedPreferences(USER_PREF,Context.MODE_PRIVATE).edit()
            .putBoolean(LOGGED_IN, false).apply()
    }

    fun deleteUserData() {
        context.getSharedPreferences(USER_PREF,Context.MODE_PRIVATE)
            .edit().clear().apply()
    }

    fun userLoggedIn() {
        context.getSharedPreferences(USER_PREF,Context.MODE_PRIVATE).edit()
            .putBoolean(LOGGED_IN, true).apply()
    }
}