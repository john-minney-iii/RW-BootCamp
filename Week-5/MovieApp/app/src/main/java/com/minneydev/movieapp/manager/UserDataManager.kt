package com.minneydev.movieapp.manager

import android.content.Context
import android.util.Log
import androidx.preference.PreferenceManager
import com.minneydev.movieapp.data.User

class UserDataManager(private val context: Context) {

    fun saveUserData(email: String, password: String, isLoggedIn: Boolean) {
        PreferenceManager.getDefaultSharedPreferences(context).edit()
            .putString("email", email)
            .putString("password", password)
            .putBoolean("isLoggedIn", isLoggedIn)
            .apply()
        Log.d("SAVE", "$email saved")

    }

    fun readUserData(): User? {
        val contents = PreferenceManager.getDefaultSharedPreferences(context).all
        return contents["isLoggedIn"]?.let {
            User(email = contents["email"].toString(),
                password = contents["password"].toString(),
                isLoggedIn = it as Boolean
            )
        }
    }

    fun logOutUser() {
        PreferenceManager.getDefaultSharedPreferences(context).edit()
            .putBoolean("isLoggedIn", false).apply()
    }

    fun deleteUserData() {
        PreferenceManager.getDefaultSharedPreferences(context)
            .edit().clear().apply()
    }

    fun userLoggedIn() {
        PreferenceManager.getDefaultSharedPreferences(context).edit()
            .putBoolean("isLoggedIn", true).apply()
    }
}