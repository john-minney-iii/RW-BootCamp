package com.minneydev.apiassignment.networking

import com.minneydev.apiassignment.models.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApiService {

    @GET("pokemon/{poke_id}")
    fun getPokemonById(@Path(value = "poke_id") pokeId: String): Call<Pokemon>

}