package com.minneydev.apiassignment.ui

import android.content.res.Resources
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.minneydev.apiassignment.MainActivity.Companion.mainContext
import com.minneydev.apiassignment.R
import com.minneydev.apiassignment.models.pokemon.Pokemon
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.launch

class PokemonHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val pokemonName: TextView = view.findViewById(R.id.pokemonName)
    private val pokemonSprite: ImageView = view.findViewById(R.id.pokemonSprite)
    private val pokemonLL: LinearLayout = view.findViewById(R.id.cardLinearLayout)

    fun bindPokemon(pokemon: Pokemon, typeColor: Int) {
        pokemonLL.setBackgroundColor(
            ContextCompat.getColor(mainContext, typeColor)
        )
        pokemonName.text = pokemon.name.capitalize()

        Picasso.get().load(pokemon.sprite_url)
            .resize(500,500)
            .into(pokemonSprite)



//        Glide.with(view).load(pokemon.sprite_url)
//            .apply(RequestOptions().override(500,500))
//            .into(pokemonSprite)
    }


}