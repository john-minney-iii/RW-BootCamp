package com.minneydev.movieapp.savingMovieData

import androidx.lifecycle.LiveData
import com.minneydev.movieapp.data.Movie

class MovieRepository(private val movieDao: MovieDao) {

    val allMovies: LiveData<List<Movie>> = movieDao.getAllMovies()

    suspend fun insert(movie: Movie) {
        movieDao.insert(movie)
    }

}