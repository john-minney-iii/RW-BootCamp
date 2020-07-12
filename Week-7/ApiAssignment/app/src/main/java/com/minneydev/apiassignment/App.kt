package com.minneydev.apiassignment

import android.app.Application
import androidx.room.Room
import com.minneydev.apiassignment.networking.PokemonApi
import com.minneydev.apiassignment.networking.buildApiService
import com.minneydev.apiassignment.savePokemonData.PokemonDatabase

class App : Application() {

    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2/"
        private val apiService by lazy { buildApiService() }
        val pokemonApi by lazy { PokemonApi(apiService) }
        lateinit var pokemonDb: PokemonDatabase
    }

    override fun onCreate() {
        super.onCreate()
        pokemonDb = Room.databaseBuilder(
            applicationContext,
            PokemonDatabase::class.java,
            "pokemon-database"
        ).build()
    }

}