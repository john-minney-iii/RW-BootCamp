package com.minneydev.pokedex.di

import android.net.ConnectivityManager
import com.minneydev.pokedex.App
import com.minneydev.pokedex.networking.NetworkStatusChecker
import org.koin.dsl.module

val networkCheckerModule = module {

    single {
        NetworkStatusChecker(App.getAppContext().getSystemService(ConnectivityManager::class.java))
    }

}
