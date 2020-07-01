package com.minneydev.movieapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.minneydev.movieapp.R
import com.minneydev.movieapp.data.Movie

class DetailAdapter(val movie: Movie) : RecyclerView.Adapter<DetailViewHolder>() {

    private val movieInfo = arrayOf(
        Pair("Summary", movie.summary),
        Pair("Release Date", movie.releaseDate)
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