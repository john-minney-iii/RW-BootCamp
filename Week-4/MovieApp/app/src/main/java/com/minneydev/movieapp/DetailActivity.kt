package com.minneydev.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.minneydev.movieapp.data.Movie
import com.minneydev.movieapp.ui.DetailAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        var movie: Movie? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        movie = intent.getParcelableExtra<Movie>(MainActivity.INTENT_MOVIE_KEY)
        initActivity(movie)

        detailRecyclerView.layoutManager = LinearLayoutManager(this)
        detailRecyclerView.adapter = DetailAdapter()

    }

    private fun initActivity(movie: Movie?) {
        if (movie != null) {
            title = movie.title
            cardTitle.text = movie.title
            Picasso.get()
                .load(movie.bannerUrl)
                .placeholder(R.drawable.image_placeholder)
                .fit()
                .into(moviePoster)
        }
    }

}