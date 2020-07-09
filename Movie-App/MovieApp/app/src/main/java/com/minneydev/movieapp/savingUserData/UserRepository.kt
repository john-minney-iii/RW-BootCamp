package com.minneydev.movieapp.savingUserData

import com.minneydev.movieapp.data.User

class UserRepository(userDb: UserDataBase) {

    private val userDao = userDb.userDao()

    suspend fun newUser(email: String, password: String) {
        val tempUser = User(email, password, true)
        userDao.newUser(tempUser)
    }

    suspend fun getUserByEmail(email: String) : User? = userDao.fetchUserByEmail(email)

    suspend fun getLoggedInUser(bool: Boolean) : User? = userDao.fetchLoggedInUser(bool)

    suspend fun logOutUser(user: User?) = userDao.logOutUser(user)

    suspend fun logInUser(user: User?) = userDao.logInUser(user)

}