package com.minneydev.pokedex.util

import com.minneydev.pokedex.App
import com.minneydev.pokedex.model.pokemon.ApiPokemon
import com.minneydev.pokedex.model.pokemon.Pokemon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonManager {

    fun savePokemon(pokemon: ApiPokemon?) : Pokemon? {
        val tempPokemon = pokemon?.let {
            Pokemon(id = it.id, name = it.name, sprite_url = it.sprites.frontDefault,
                type = it.types[0].type.name)
        }
        if (tempPokemon != null) {
            CoroutineScope(Dispatchers.IO).launch {
                App.pokemonDb.pokemonDao().insertPokemon(tempPokemon)
            }
        }
        return tempPokemon
    }

    fun getFirstId(gen: Int) : String {
        return when (gen) {
            1 -> "1"
            2 -> "152"
            3 -> "252"
            4 -> "387"
            else -> "0"
        }
    }

}
