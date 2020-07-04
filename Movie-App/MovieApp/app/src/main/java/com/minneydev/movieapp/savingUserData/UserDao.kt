package com.minneydev.movieapp.savingUserData

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.minneydev.movieapp.data.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun newUser(user: User)

    @Query("DELETE FROM user_table")
    fun deleteAll()

    @Query("SELECT * FROM user_table WHERE userEmail=:email")
    fun fetchUserByEmail(email: String): User?

    @Query("SELECT * FROM user_table WHERE isLoggedIn=:bool")
    fun fetchLoggedInUser(bool: Boolean): User?


}