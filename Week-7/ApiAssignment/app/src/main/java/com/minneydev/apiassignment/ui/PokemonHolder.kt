package com.minneydev.apiassignment.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.minneydev.apiassignment.R
import kotlinx.android.synthetic.main.pokemon_card.view.*

class PokemonHolder(view: View) : RecyclerView.ViewHolder(view) {

    val pokemonName: TextView = view.findViewById(R.id.pokemonName)

}