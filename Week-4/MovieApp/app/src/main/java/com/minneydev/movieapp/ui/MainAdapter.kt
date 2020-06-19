package com.minneydev.movieapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.minneydev.movieapp.R

class MainAdapter : RecyclerView.Adapter<MainViewHolder>() {

    private val placeHolderStrings = arrayOf(
        "Android Dev", "Kotlin", "Jimmy Johns", "Laptop"
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.main_card, parent, false)
        return MainViewHolder(view)
    }

    override fun getItemCount(): Int {
        return placeHolderStrings.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.movieTitle.text = placeHolderStrings[position]
    }

}