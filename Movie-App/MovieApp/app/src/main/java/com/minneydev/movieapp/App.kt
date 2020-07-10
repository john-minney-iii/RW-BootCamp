package com.minneydev.movieapp

import android.app.Application
import com.minneydev.movieapp.userSharedPrefs.UserSharedPreferences

class App : Application() {

    companion object {
        private lateinit var instance: App
        lateinit var userDataManager: UserSharedPreferences
        fun getAppContext() = instance
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
        initRoomUser()
    }

    private fun initRoomUser() {
        userDataManager = UserSharedPreferences(this)
    }


}