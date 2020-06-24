package com.minneydev.movieapp

import android.content.Intent
import android.net.Uri
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
        movie = intent.getParcelableExtra(MainActivity.INTENT_MOVIE_KEY)
        displayMovie(movie)

        detailRecyclerView.layoutManager = LinearLayoutManager(this)
        detailRecyclerView.adapter = DetailAdapter()

        searchMovieFab.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(movie?.imdb)
            startActivity(intent)
        }

    }

    private fun displayMovie(movie: Movie?) {
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