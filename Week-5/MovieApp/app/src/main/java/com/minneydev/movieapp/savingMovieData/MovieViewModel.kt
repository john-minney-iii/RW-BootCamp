package com.minneydev.movieapp.savingMovieData

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.minneydev.movieapp.data.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MovieViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MovieRepository

    val allMovies: LiveData<List<Movie>>

    init {
        val movieDao = MovieDataBase.getDatabase(application, viewModelScope).movieDao()
        repository = MovieRepository(movieDao)
        allMovies = repository.allMovies
    }

    fun insert(movie: Movie) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(movie)
    }

    fun getByTitle(title: String) : Movie = runBlocking {
        repository.getByTitle(title)
    }

}