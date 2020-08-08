package com.minneydev.pokedex.di

import com.minneydev.pokedex.networking.PokemonApi
import com.minneydev.pokedex.repository.PokemonRepository
import org.koin.dsl.module

val repositoryModule = module {

    single {
        PokemonApi(get())
    }

    single {
        PokemonRepository(get(), get())
    }

}