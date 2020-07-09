package com.minneydev.movieapp.savingMovieData

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.minneydev.movieapp.data.Movie
import com.minneydev.movieapp.data.placeHolderMovie
import kotlinx.coroutines.*

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

    suspend fun getByTitle(movieTitle: String) : Movie? {
        var movie: Movie? = withContext(Dispatchers.IO) {
            repository.getByTitle(movieTitle)
        }
        if (movie != null) { return movie }
        return placeHolderMovie
    }

    /*
        fun getByTitle(movieTitle: String) : Movie? = runBlocking {
            repository.getByTitle(movieTitle)
        }
     */

}