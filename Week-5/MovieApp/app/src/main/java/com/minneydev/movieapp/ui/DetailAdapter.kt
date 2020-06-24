package com.minneydev.movieapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.minneydev.movieapp.DetailActivity
import com.minneydev.movieapp.DetailFragment
import com.minneydev.movieapp.R

class DetailAdapter : RecyclerView.Adapter<DetailViewHolder>() {

    private val currentMovie = DetailFragment.movie

    private val movieInfo = arrayOf(
        Pair("Summary", currentMovie?.summary),
        Pair("Release Date", currentMovie?.releaseDate)
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.detail_card, parent, false)
        return DetailViewHolder(view)
    }

    override fun getItemCount() : Int  = movieInfo.size

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.bind(movieInfo[position].first,  movieInfo[position].second)

    }

}