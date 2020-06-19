package com.minneydev.movieapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.minneydev.movieapp.R
import com.minneydev.movieapp.data.Movie
import com.minneydev.movieapp.data.MovieData
import com.squareup.picasso.Picasso

class MainAdapter : RecyclerView.Adapter<MainViewHolder>() {

    private val movies: Array<Movie> = MovieData.getMovies()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.main_card, parent, false)
        return MainViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.movieTitle.text = movies[position].title
        Picasso.get()
            .load(movies[position].posterUrl)
            .placeholder(R.drawable.image_placeholder)
            .resize(339,500)
            .into(holder.moviePoster)
    }

}