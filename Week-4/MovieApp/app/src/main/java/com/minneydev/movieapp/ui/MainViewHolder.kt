package com.minneydev.movieapp.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.minneydev.movieapp.R

class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    var movieTitle = view.findViewById<TextView>(R.id.movieName)
    var moviePoster = view.findViewById<ImageView>(R.id.moviePoster)

}