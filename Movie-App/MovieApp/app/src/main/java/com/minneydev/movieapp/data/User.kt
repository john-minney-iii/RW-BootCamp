package com.minneydev.movieapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey @ColumnInfo(name = "userEmail") val  email: String,
    val password: String,
    @ColumnInfo(name = "isLoggedIn") val isLoggedIn: Boolean = false)