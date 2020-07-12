package com.minneydev.apiassignment.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.minneydev.apiassignment.R
import com.minneydev.apiassignment.models.pokemon.ApiPokemon
import com.minneydev.apiassignment.models.pokemon.Pokemon
import com.squareup.picasso.Picasso

class PokemonAdapter() : RecyclerView.Adapter<PokemonHolder>() {

    private val pokeSet = mutableSetOf<Pokemon>()

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
        holder.pokemonName.text = currentPokemon.name?.capitalize()
        Picasso.get().load(currentPokemon.sprite_url)
            .resize(500,500)
            .into(holder.pokemonSprite)
        Log.d("TEST", "${currentPokemon.name}" +
                ",${currentPokemon.type}")
    }

    fun setPokemon(pokemon: Pokemon?) {
        if (pokemon != null) {
            pokeSet.add(pokemon)
        }
        notifyDataSetChanged()
    }

}