package com.minneydev.movieapp.savingMovieData

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.minneydev.movieapp.data.Movie
import com.minneydev.movieapp.data.getMoviesArray
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Database (entities = [Movie::class], version = 1, exportSchema = true)
abstract class MovieDataBase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    private class MovieDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let {database ->
                scope.launch {
                    var movieDao = database.movieDao()
                    movieDao.deleteAll()
                    getMoviesArray().forEach {movie ->
                        movieDao.insert(movie)
                    }
                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: MovieDataBase? = null

        fun getDatabase(context: Context,
                        scope: CoroutineScope): MovieDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDataBase::class.java,
                    "movie_database"
                ).addCallback(MovieDatabaseCallback(scope)).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}