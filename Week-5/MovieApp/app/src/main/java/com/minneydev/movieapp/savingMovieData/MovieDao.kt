package com.minneydev.movieapp.savingMovieData

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.minneydev.movieapp.data.Movie

@Dao
interface MovieDao {

    @Query ("SELECT * FROM movie_table")
    fun getAllMovies(): LiveData<List<Movie>>

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(movie: Movie)

    @Query ("DELETE FROM movie_table")
    suspend fun deleteAll()


}