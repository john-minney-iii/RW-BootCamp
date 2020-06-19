package com.minneydev.movieapp.data

data class Movie(
    val id: Int,
    val releaseDate: String,
    val title: String,
    val summary: String,
    var posterUrl: String = ""
) {



}
