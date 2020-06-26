package com.minneydev.movieapp.manager

import android.content.Context
import android.util.Log
import androidx.preference.PreferenceManager
import com.minneydev.movieapp.data.User

class UserDataManager(private val context: Context) {

    fun saveUserData(email: String, password: String, isLoggedIn: Boolean) {
        val sharedPrefs =
            PreferenceManager.getDefaultSharedPreferences(context).edit()
        sharedPrefs.putString("email", email)
        sharedPrefs.putString("password", password)
        sharedPrefs.putBoolean("isLoggedIn", isLoggedIn)
        sharedPrefs.apply()
        Log.d("SAVE", "$email saved")

    }

    fun readUserData(): User? {
        val sharedPrefs =
            PreferenceManager.getDefaultSharedPreferences(context)
        val contents = sharedPrefs.all
        return contents["isLoggedIn"]?.let {
            User(email = contents["email"].toString(),
                password = contents["password"].toString(),
                isLoggedIn = it as Boolean
            )
        }
    }

    fun deleteUserData() {
        TODO("Implement Later")
    }
}