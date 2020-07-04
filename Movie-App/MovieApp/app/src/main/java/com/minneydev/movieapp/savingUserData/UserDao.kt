package com.minneydev.movieapp.savingUserData

import androidx.room.*
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

    @Update
    fun logOutUser(user: User)

    @Update
    fun logInUser(user: User)


}