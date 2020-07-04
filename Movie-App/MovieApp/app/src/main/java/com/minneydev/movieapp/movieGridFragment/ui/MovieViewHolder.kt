package com.minneydev.movieapp.movieGridFragment.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.minneydev.movieapp.R
import com.squareup.picasso.Picasso

class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    var movieTitle = view.findViewById<TextView>(R.id.movieName)
    var moviePoster = view.findViewById<ImageView>(R.id.moviePoster)

    fun bind(title: String, url: String) {
        movieTitle.text = title
        Picasso.get()
            .load(url)
            .placeholder(R.drawable.image_placeholder)
            .resize(339,500)
            .into(moviePoster)
    }

}