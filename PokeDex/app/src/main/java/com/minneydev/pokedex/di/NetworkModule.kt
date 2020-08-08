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
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        val client = OkHttpClient().newBuilder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) { client.addInterceptor(get<HttpLoggingInterceptor>()) }
        client.build()
    }

    single (named("moshi_conv_factory")){
        MoshiConverterFactory.create().asLenient()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(get<String>(named("BASE_URL")))
            .addConverterFactory(get(named("moshi_conv_factory")))
            .client(get())
            .build()
    }

    single {
        get<Retrofit>().create(PokemonApiService::class.java)
    }

}

/*

fun buildClient(): OkHttpClient =
    OkHttpClient.Builder()
        .build()

fun buildRetrofit(): Retrofit {
    return Retrofit.Builder()
        .client(buildClient())
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create().asLenient())
        .build()
}

fun buildApiService(): PokemonApiService =
    buildRetrofit().create(PokemonApiService::class.java)
 */