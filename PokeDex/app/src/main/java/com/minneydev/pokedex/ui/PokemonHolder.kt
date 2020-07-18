package com.minneydev.pokedex.ui

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.minneydev.pokedex.App
import com.minneydev.pokedex.R
import com.minneydev.pokedex.model.pokemon.Pokemon

class PokemonHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val pokemonName: TextView = view.findViewById(R.id.pokemonName)
    private val pokemonSprite: ImageView = view.findViewById(R.id.pokemonSprite)
    private val pokemonLL: LinearLayout = view.findViewById(R.id.cardLinearLayout)

    fun bindPokemon(pokemon: Pokemon, typeColor: Int) {
        pokemonLL.setBackgroundColor(
            ContextCompat.getColor(App.getAppContext(), typeColor)
        )
        pokemonName.text = pokemon.name.capitalize()
        Glide.with(view).load(pokemon.sprite_url)
            .apply(RequestOptions().override(500, 500).fitCenter())
            .into(pokemonSprite)
    }


}