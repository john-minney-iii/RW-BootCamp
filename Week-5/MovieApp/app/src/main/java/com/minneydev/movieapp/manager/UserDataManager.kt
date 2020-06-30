package com.minneydev.movieapp.manager

import android.content.Context
import android.util.Log
import com.minneydev.movieapp.data.User

class UserDataManager(private val context: Context) {

    private val USER_PREF = "user"

    fun saveUserData(email: String, password: String, isLoggedIn: Boolean) {
        context.getSharedPreferences(USER_PREF,Context.MODE_PRIVATE).edit()
            .putString("email", email)
            .putString("password", password)
            .putBoolean("isLoggedIn", isLoggedIn)
            .apply()
        Log.d("SAVE", "$email saved")

    }

    fun readUserData(): User? {
        val contents = context.getSharedPreferences(USER_PREF,Context.MODE_PRIVATE).all
        return contents["isLoggedIn"]?.let {
            User(email = contents["email"].toString(),
                password = contents["password"].toString(),
                isLoggedIn = it as Boolean
            )
        }
    }

    fun readUserEmail(): String? {
        return context.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE)
            .getString("email", "")
    }

    fun readUserPassword(): String? {
        return context.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE)
            .getString("password", "")
    }

    fun readIsLoggedIn(): Boolean? {
        return context.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE)
            .getBoolean("isLoggedIn", false)
    }

    fun logOutUser() {
        context.getSharedPreferences(USER_PREF,Context.MODE_PRIVATE).edit()
            .putBoolean("isLoggedIn", false).apply()
    }

    fun deleteUserData() {
        context.getSharedPreferences(USER_PREF,Context.MODE_PRIVATE)
            .edit().clear().apply()
    }

    fun userLoggedIn() {
        context.getSharedPreferences(USER_PREF,Context.MODE_PRIVATE).edit()
            .putBoolean("isLoggedIn", true).apply()
    }
}