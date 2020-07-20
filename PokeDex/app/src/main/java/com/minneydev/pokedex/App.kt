package com.minneydev.pokedex

import android.app.Application
import androidx.room.Room
import com.minneydev.pokedex.networking.PokemonApi
import com.minneydev.pokedex.networking.buildApiService
import com.minneydev.pokedex.savePokemonData.PokemonDatabase

class App : Application() {

    companion object {
        private lateinit var instance: App
        const val BASE_URL = "https://pokeapi.co/api/v2/"
        const val TAG = "PokeDex"
        const val DOWNLOAD_WORKER = "DOWNLOAD"
        const val REFRESH_WORKER = "REFRESH"
        private val apiService by lazy { buildApiService() }
        val pokemonApi by lazy { PokemonApi(apiService) }
        lateinit var pokemonDb: PokemonDatabase
        fun getAppContext() = instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        pokemonDb = Room.databaseBuilder(
            applicationContext,
            PokemonDatabase::class.java,
            "pokemon-database"
        ).build()

    }

}