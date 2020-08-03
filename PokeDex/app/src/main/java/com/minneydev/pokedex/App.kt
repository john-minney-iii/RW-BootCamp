package com.minneydev.pokedex

import android.app.Application
import android.net.ConnectivityManager
import androidx.room.Room
import com.minneydev.pokedex.di.appModule
import com.minneydev.pokedex.di.networkModule
import com.minneydev.pokedex.di.pokemonManagerModule
import com.minneydev.pokedex.di.repositoryModule
import com.minneydev.pokedex.networking.NetworkStatusChecker
import com.minneydev.pokedex.savePokemonData.PokemonDatabase
import com.minneydev.pokedex.util.DefaultCurrentActivityListener
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    companion object {
        private lateinit var instance: App
        lateinit var pokemonDb: PokemonDatabase
        fun getAppContext() = instance
        val networkStatusChecker by lazy {
            NetworkStatusChecker(getAppContext().getSystemService(ConnectivityManager::class.java))
        }
    }

    override fun onCreate() {
        val defaultCurrentActivityListener: DefaultCurrentActivityListener by inject()
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(listOf(networkModule, appModule, repositoryModule, pokemonManagerModule))
        }
        registerActivityLifecycleCallbacks(defaultCurrentActivityListener)
        instance = this
        pokemonDb = Room.databaseBuilder(
            applicationContext,
            PokemonDatabase::class.java,
            "pokemon-database")
            .build()
    }

}