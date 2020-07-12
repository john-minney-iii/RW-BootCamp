package com.minneydev.apiassignment.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.minneydev.apiassignment.R
import com.minneydev.apiassignment.models.Pokemon
import com.squareup.picasso.Picasso

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
        val currentPokemon = pokeSet.elementAt(position)
        holder.pokemonName.text = currentPokemon?.name?.capitalize()
        Picasso.get().load(currentPokemon?.sprites?.frontDefault)
            .resize(500,500)
            .into(holder.pokemonSprite)
        Log.d("TEST", "${currentPokemon?.name?.capitalize()}" +
                ",${currentPokemon?.types?.get(0)?.type?.name}")
    }

}