package com.minneydev.apiassignment.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.minneydev.apiassignment.R
import com.minneydev.apiassignment.models.pokemon.Pokemon

class PokemonHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    val pokemonName: TextView = view.findViewById(R.id.pokemonName)
    val pokemonSprite: ImageView = view.findViewById(R.id.pokemonSprite)

    fun bindPokemon(pokemon: Pokemon) {
        pokemonName.text = pokemon.name.capitalize()
        Glide.with(view).load(pokemon.sprite_url)
            .apply(RequestOptions().override(500,500))
            .into(pokemonSprite)

    }



}