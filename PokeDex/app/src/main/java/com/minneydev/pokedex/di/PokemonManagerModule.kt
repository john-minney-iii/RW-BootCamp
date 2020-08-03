package com.minneydev.pokedex.di

import com.minneydev.pokedex.util.PokemonManager
import org.koin.dsl.module

val pokemonManagerModule = module {

    single {
        val pokemonManager = PokemonManager()
        pokemonManager
    }

}