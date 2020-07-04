package com.minneydev.movieapp.savingUserData

import com.minneydev.movieapp.data.User

class UserRepository(userDb: UserDataBase) {

    private val userDao = userDb.userDao()

    fun newUser(email: String, password: String) {
        val tempUser = User(email, password, true)
        userDao.newUser(tempUser)
    }

    fun getUserByEmail(email: String) : User? = userDao.fetchUserByEmail(email)

    fun getLoggedInUser(bool: Boolean) : User? = userDao.fetchLoggedInUser(bool)

}