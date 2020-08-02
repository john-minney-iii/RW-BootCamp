package com.minneydev.pokedex.repository

import com.minneydev.pokedex.App.Companion.pokemonDb
import com.minneydev.pokedex.model.pokemon.ApiPokemon
import com.minneydev.pokedex.model.pokemon.Pokemon
import com.minneydev.pokedex.networking.PokemonApi
import com.minneydev.pokedex.networking.PokemonApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * Does all the talking between the [PokemonApi] and the [PokemonDatabase]
 */

class PokemonRepository : KoinComponent {

    private val pokemonService: PokemonApiService by inject()
    private val pokemonApi = PokemonApi(pokemonService)

    fun savePokemon(pokemon: ApiPokemon?) : Pokemon? {
        val tempPokemon = pokemon?.mapToPokemon()
        if (tempPokemon != null) {
            CoroutineScope(Dispatchers.IO).launch {
                pokemonDb.pokemonDao().insertPokemon(tempPokemon)
            }
        }
        return tempPokemon
    }

    fun nukeDatabase() {
        CoroutineScope(Dispatchers.IO).launch {
            pokemonDb.pokemonDao().nukeTable()
        }
    }

    suspend fun fetchPokemonById(id: String) : ApiPokemon? {
        return pokemonApi.fetchPokemonById(id)
    }

    suspend fun fetchAllPokemon() : List<Pokemon> {
        return pokemonDb.pokemonDao().getAllPokemon()
    }




}