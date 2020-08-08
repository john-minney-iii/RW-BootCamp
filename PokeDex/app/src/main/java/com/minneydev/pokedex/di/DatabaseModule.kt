package com.minneydev.pokedex.di

import androidx.room.Room
import com.minneydev.pokedex.App
import com.minneydev.pokedex.savePokemonData.PokemonDatabase
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(
            App.getAppContext(),
            PokemonDatabase::class.java,
            "pokemon-database")
            .build()
    }

}