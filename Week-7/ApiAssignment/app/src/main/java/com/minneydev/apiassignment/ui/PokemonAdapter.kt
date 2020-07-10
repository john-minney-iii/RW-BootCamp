package com.minneydev.apiassignment.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.minneydev.apiassignment.R

class PokemonAdapter : RecyclerView.Adapter<PokemonHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_card, parent, false)
        return PokemonHolder(view)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: PokemonHolder, position: Int) {
        TODO("Not yet implemented")
    }
}