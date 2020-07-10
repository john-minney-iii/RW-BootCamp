package com.minneydev.apiassignment.networking

import android.util.Log
import com.google.gson.Gson
import com.minneydev.apiassignment.BASE_URL
import com.minneydev.apiassignment.models.Pokemon
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class PokemonApi {

    private val gson = Gson()

    fun fetchFirstGenPokemon() {
        Thread(Runnable {
            val connection = URL("$BASE_URL/pokemon/2").openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.setRequestProperty("Content-Type", "application/json")
            connection.setRequestProperty("Accept", "application/json")
            connection.readTimeout = 10000
            connection.connectTimeout = 10000
            connection.doOutput = true
            connection.doInput = true

            try {
                val reader = InputStreamReader(connection.inputStream)
                reader.use {input ->
                    val response = StringBuilder()
                    val bufferedReader = BufferedReader(input)

                    bufferedReader.useLines { lines ->
                        lines.forEach {
                            response.append(it.trim())
                        }
                    }
                    val fetchedPokemonList = gson.fromJson(response.toString(), Pokemon::class.java)
                    Log.d("FETCH", "$fetchedPokemonList")
                }
            }catch (error: Throwable) {

            }



            connection.disconnect()
        }).start()
    }

}