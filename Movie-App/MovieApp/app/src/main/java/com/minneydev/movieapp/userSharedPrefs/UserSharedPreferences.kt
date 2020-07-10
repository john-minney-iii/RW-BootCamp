package com.minneydev.movieapp.userSharedPrefs

import android.content.Context
import com.minneydev.movieapp.data.User

const val USER_SHARED_PREFS = "user-prefs"
const val USER_EMAIL = "email"
const val USER_PASS = "password"
const val IS_LOGGED_IN = "isLoggedIn"

class UserSharedPreferences(val context: Context) {

    private val userSharedPrefs = context.getSharedPreferences(USER_SHARED_PREFS, Context.MODE_PRIVATE)
    private val editor = userSharedPrefs.edit()

    fun saveNewUser(email: String, password: String) {
        editor.putString(USER_EMAIL, email)
        editor.putString(USER_PASS, password)
        editor.putBoolean(IS_LOGGED_IN, true)
        editor.apply()
    }

    fun getLoggedInUser() : User? {
        if (userSharedPrefs.getBoolean(IS_LOGGED_IN, false)) {
            val email = userSharedPrefs.getString(USER_EMAIL, null)
            val password = userSharedPrefs.getString(USER_PASS, null)
            if (email != null && password != null) {
                return User(email = email, password = password, isLoggedIn = true)
            }
        }
        return null
    }

    fun logOutUser() {
        editor.putBoolean(IS_LOGGED_IN, false)
        editor.apply()
    }

    fun logInUser() {
        editor.putBoolean(IS_LOGGED_IN, true)
        editor.apply()
    }

    fun fetchUser() : User? {
        val email = userSharedPrefs.getString(USER_EMAIL, null)
        val password = userSharedPrefs.getString(USER_PASS, null)
        if (email != null && password != null) {
            return User(email, password, true)
        }
        return null
    }

}