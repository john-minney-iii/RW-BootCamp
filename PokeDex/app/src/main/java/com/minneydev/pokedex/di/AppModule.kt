package com.minneydev.pokedex.di

import com.minneydev.pokedex.util.ActivityRetriever
import com.minneydev.pokedex.util.DefaultCurrentActivityListener
import org.koin.dsl.module

val appModule = module {

    single { DefaultCurrentActivityListener() }
    single { ActivityRetriever(get()) }

}