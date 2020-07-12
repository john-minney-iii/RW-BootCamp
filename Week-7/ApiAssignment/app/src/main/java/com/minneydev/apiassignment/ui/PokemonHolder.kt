package com.minneydev.apiassignment.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.minneydev.apiassignment.R

class PokemonHolder(view: View) : RecyclerView.ViewHolder(view) {

    val pokemonName: TextView = view.findViewById(R.id.pokemonName)
    val pokemonSprite: ImageView = view.findViewById(R.id.pokemonSprite)

}