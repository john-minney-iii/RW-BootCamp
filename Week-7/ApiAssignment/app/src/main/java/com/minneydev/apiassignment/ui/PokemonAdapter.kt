package com.minneydev.apiassignment.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.minneydev.apiassignment.R
import com.minneydev.apiassignment.models.pokemon.Pokemon

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
        holder.bindPokemon(currentPokemon, getTypeColor(currentPokemon.type))
    }

    fun setPokemon(pokemon: Pokemon?) {
        if (pokemon != null) {
            pokeSet.add(pokemon)
        }
        notifyDataSetChanged()
    }

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
            else -> R.color.normalType
        }
    }

}