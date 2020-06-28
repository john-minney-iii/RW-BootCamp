package com.minneydev.movieapp.data

data class User(val email: String, val password: String, var isLoggedIn: Boolean = false) {

}