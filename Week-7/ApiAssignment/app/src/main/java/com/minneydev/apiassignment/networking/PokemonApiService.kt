package com.minneydev.apiassignment.networking

import com.minneydev.apiassignment.models.pokemon.ApiPokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApiService {

    @GET("pokemon/{poke_id}")
    fun getPokemonById(@Path(value = "poke_id") pokeId: String): Call<ApiPokemon>
    //This is fine, but there is a better way to work with coroutines
    /*
    The way you used coroutines with Retrofit is okay.
    You could have used a different approach though. If you put a suspend modifier on this method,
    and put return type as just ApiPokemon, retrofit will do everything for you and you will get
    the result back when it's ready.
        - Luka
     */

}