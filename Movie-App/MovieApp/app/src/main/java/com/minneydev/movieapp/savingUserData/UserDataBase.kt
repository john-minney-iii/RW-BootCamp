package com.minneydev.movieapp.savingUserData

import androidx.room.Database
import androidx.room.RoomDatabase
import com.minneydev.movieapp.data.User

const val USERDATABASE_NAME = "user-database"

@Database (entities = [User::class], version = 1, exportSchema = false)
abstract class UserDataBase : RoomDatabase() {

    abstract fun userDao(): UserDao

}