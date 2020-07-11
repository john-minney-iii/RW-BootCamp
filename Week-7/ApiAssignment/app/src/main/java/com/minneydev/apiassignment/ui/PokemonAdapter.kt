package com.minneydev.apiassignment.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.minneydev.apiassignment.R
import com.minneydev.apiassignment.models.Pokemon

class PokemonAdapter(private val pokeSet: Set<Pokemon?>) : RecyclerView.Adapter<PokemonHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_card, parent, false)
        return PokemonHolder(view)
    }

    override fun getItemCount(): Int {
        return pokeSet.size
    }

    override fun onBindViewHolder(holder: PokemonHolder, position: Int) {
        holder.pokemonName.text = pokeSet.elementAt(position)?.name?.capitalize()
    }

}