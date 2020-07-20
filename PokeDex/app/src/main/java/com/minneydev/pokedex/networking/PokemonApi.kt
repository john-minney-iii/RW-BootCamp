package com.minneydev.pokedex.networking


import com.minneydev.pokedex.model.pokemon.ApiPokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonApi(private val pokemonApiService: PokemonApiService) {

    suspend fun fetchPokemonById(id: String) : ApiPokemon? = withContext(Dispatchers.IO) {
        return@withContext pokemonApiService.getPokemonById(id).execute().body()
    }

}