package com.minneydev.apiassignment

import android.app.Application
import com.minneydev.apiassignment.networking.PokemonApi
import com.minneydev.apiassignment.networking.buildApiService

class App : Application() {

    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2/"
        private val apiService by lazy { buildApiService() }
        val pokemonApi by lazy { PokemonApi(apiService) }
    }

    override fun onCreate() {
        super.onCreate()
    }

}