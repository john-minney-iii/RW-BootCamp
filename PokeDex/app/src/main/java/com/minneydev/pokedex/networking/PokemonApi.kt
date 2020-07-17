package com.minneydev.pokedex.networking


import com.minneydev.pokedex.model.pokemon.ApiPokemon
import com.minneydev.pokedex.model.pokemon.Pokemon
import com.minneydev.pokedex.model.pokemon.PokemonListCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call

class PokemonApi(private val pokemonApiService: PokemonApiService) {

    suspend fun fetchPokemonById(id: String) : ApiPokemon? = withContext(Dispatchers.IO) {
        return@withContext pokemonApiService.getPokemonById(id).execute().body()
    }

}