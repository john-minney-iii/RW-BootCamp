package com.minneydev.movieapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.minneydev.movieapp.R
import com.minneydev.movieapp.data.Movie
import com.minneydev.movieapp.data.getMoviesArray
import com.squareup.picasso.Picasso

class MovieAdapter(private val clickListener: MovieClickListener) : RecyclerView.Adapter<MovieViewHolder>() {

    interface MovieClickListener {
        fun movieClicked(movie: Movie)
    }

    private val movies: Array<Movie> = getMoviesArray()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.main_card, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position].title, movies[position].posterUrl)
        holder.itemView.setOnClickListener {
            clickListener.movieClicked(movies[position])
        }
    }

}