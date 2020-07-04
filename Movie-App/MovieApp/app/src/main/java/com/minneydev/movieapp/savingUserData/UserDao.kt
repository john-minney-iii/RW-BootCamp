package com.minneydev.movieapp.savingUserData

import androidx.room.*
import com.minneydev.movieapp.data.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun newUser(user: User)

    @Query("DELETE FROM user_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM user_table WHERE userEmail=:email")
    suspend fun fetchUserByEmail(email: String): User?

    @Query("SELECT * FROM user_table WHERE isLoggedIn=:bool")
    suspend fun fetchLoggedInUser(bool: Boolean): User?

    @Update
    suspend fun logOutUser(user: User?)

    @Update
    suspend fun logInUser(user: User?)


}