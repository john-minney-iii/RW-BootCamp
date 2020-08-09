package com.minneydev.pokedex

import android.app.Application
import android.content.Intent
import android.net.ConnectivityManager
import androidx.room.Room
import com.minneydev.pokedex.di.*
import com.minneydev.pokedex.model.pokemon.Pokemon
import com.minneydev.pokedex.networking.NetworkStatusChecker
import com.minneydev.pokedex.savePokemonData.PokemonDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    companion object {
        private lateinit var instance: App
        fun getAppContext() = instance
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(listOf(networkModule, repositoryModule,
                pokemonManagerModule, networkCheckerModule,
                databaseModule))
        }
        instance = this
    }

}