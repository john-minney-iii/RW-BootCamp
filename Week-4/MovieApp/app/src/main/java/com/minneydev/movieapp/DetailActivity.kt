package com.minneydev.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.minneydev.movieapp.data.Movie

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val movie = intent.getParcelableExtra<Movie>(MainActivity.INTENT_MOVIE_KEY)
        title = movie?.title
    }
}