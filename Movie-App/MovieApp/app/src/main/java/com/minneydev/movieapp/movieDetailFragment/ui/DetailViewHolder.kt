package com.minneydev.movieapp.movieDetailFragment.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.minneydev.movieapp.R

class DetailViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val title = view.findViewById<TextView>(R.id.cardTitle)
    val message = view.findViewById<TextView>(R.id.cardMessage)

    fun bind(t: String?, m: String?) {
        title.text = t
        message.text = m
    }
}