package com.minneydev.pokedex

import android.app.Application
import android.net.ConnectivityManager
import androidx.room.Room
import com.minneydev.pokedex.networking.NetworkStatusChecker
import com.minneydev.pokedex.networking.PokemonApi
import com.minneydev.pokedex.networking.buildApiService
import com.minneydev.pokedex.savePokemonData.PokemonDatabase

class App : Application() {

    companion object {
        private lateinit var instance: App
        const val TAG = "PokeDex"
        lateinit var pokemonDb: PokemonDatabase
        val apiService by lazy { buildApiService() }
        val pokemonApi by lazy { PokemonApi(apiService) }
        fun getAppContext() = instance
        val networkStatusChecker by lazy {
            NetworkStatusChecker(getAppContext().getSystemService(ConnectivityManager::class.java))
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        pokemonDb = Room.databaseBuilder(
            applicationContext,
            PokemonDatabase::class.java,
            "pokemon-database")
            .build()
    }

}