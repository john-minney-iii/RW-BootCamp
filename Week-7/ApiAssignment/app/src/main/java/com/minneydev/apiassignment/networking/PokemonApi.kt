package com.minneydev.apiassignment.networking


import com.minneydev.apiassignment.models.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonApi(private val pokemonApiService: PokemonApiService) {

    suspend fun fetchPokemonById(id: String) : Pokemon? = withContext(Dispatchers.IO) {
        return@withContext pokemonApiService.getPokemonById(id).execute().body()
    }


}