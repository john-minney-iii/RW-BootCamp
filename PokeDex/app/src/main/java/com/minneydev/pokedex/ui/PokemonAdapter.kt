package com.minneydev.pokedex.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.minneydev.pokedex.R
import com.minneydev.pokedex.model.pokemon.Pokemon

/**
 * Adapter for [R.id.pokemonRecyclerView]
 */

class PokemonAdapter() : RecyclerView.Adapter<PokemonHolder>() {

    private val pokeSet = mutableSetOf<Pokemon>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_card, parent, false)
        return PokemonHolder(view)
    }

    override fun getItemCount() = pokeSet.size

    override fun onBindViewHolder(holder: PokemonHolder, position: Int) {
        val currentPokemon = pokeSet.elementAt(position)
        holder.bindPokemon(currentPokemon, getTypeColor(currentPokemon.type))
    }

    fun setPokemon(pokemon: Pokemon?) {
        if (pokemon != null) {
            pokeSet.add(pokemon)
        }
        notifyDataSetChanged()
    }

    fun clear() {
        pokeSet.clear()
        notifyDataSetChanged()
    }

    //This doesn't have all the types for Gen 2,3,4. I'll have to come back and add them.
    private fun getTypeColor(type: String) : Int {
        return when (type) {
            "bug" -> R.color.bugType
            "dragon" -> R.color.dragonType
            "electric" -> R.color.electricType
            "fighting" -> R.color.fightingType
            "fire" -> R.color.fireType
            "flying" -> R.color.flyingType
            "ghost" -> R.color.ghostType
            "grass" -> R.color.grassType
            "ground" -> R.color.groundType
            "ice" -> R.color.iceType
            "normal" -> R.color.normalType
            "poison" -> R.color.poisonType
            "psychic" -> R.color.psychicType
            "rock" -> R.color.rockType
            "water" -> R.color.waterType
            "steel" -> R.color.steelType
            "dark" -> R.color.darkType
            "fairy" -> R.color.fairyType
            else -> R.color.normalType
        }
    }

}