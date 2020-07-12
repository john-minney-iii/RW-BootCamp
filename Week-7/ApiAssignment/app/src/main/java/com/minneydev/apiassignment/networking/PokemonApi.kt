package com.minneydev.apiassignment.networking


import com.minneydev.apiassignment.models.pokemon.ApiPokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonApi(private val pokemonApiService: PokemonApiService) {

    suspend fun fetchPokemonById(id: String) : ApiPokemon? = withContext(Dispatchers.IO) {
        return@withContext pokemonApiService.getPokemonById(id).execute().body()
    }


}