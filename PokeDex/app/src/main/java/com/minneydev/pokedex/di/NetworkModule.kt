package com.minneydev.pokedex.di

import com.minneydev.pokedex.BuildConfig
import com.minneydev.pokedex.networking.PokemonApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    single(named("BASE_URL")) {
        "https://pokeapi.co/api/v2/"
    }

    single {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        interceptor
    }

    single {
        val client = OkHttpClient().newBuilder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) { client.addInterceptor(get<HttpLoggingInterceptor>()) }
        client.build()
    }

    single {
        Retrofit.Builder().baseUrl(get<String>(named("BASE_URL")))
            .addConverterFactory(MoshiConverterFactory.create().asLenient())
            .client(get())
            .build()
    }

    single {
        get<Retrofit>().create(PokemonApiService::class.java)
    }

}