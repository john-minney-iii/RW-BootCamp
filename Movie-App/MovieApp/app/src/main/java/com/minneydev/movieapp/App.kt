package com.minneydev.movieapp

import android.app.Application
import androidx.room.Room
import com.minneydev.movieapp.savingUserData.USERDATABASE_NAME
import com.minneydev.movieapp.savingUserData.UserDataBase

class App : Application() {

    companion object {
        private lateinit var instance: App
        lateinit var userDatabase: UserDataBase
        fun getAppContext() = instance
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
        initRoomUser()
    }

    private fun initRoomUser() {
        userDatabase = Room.databaseBuilder(this, UserDataBase::class.java, USERDATABASE_NAME)
            .build()
    }


}