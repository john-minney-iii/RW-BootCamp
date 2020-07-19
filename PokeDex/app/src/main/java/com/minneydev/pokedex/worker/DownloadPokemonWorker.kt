package com.minneydev.pokedex.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.minneydev.pokedex.App
import com.minneydev.pokedex.App.Companion.TAG
import com.minneydev.pokedex.MainActivity
import com.minneydev.pokedex.util.PokemonManager
import kotlinx.coroutines.*

class DownloadPokemonWorker(context: Context, workerParameters: WorkerParameters) :
                    Worker(context, workerParameters) {

    private val pokemonManager by lazy { PokemonManager() }

    override fun doWork(): Result {
        downloadPokemon(MainActivity.currentGen)
//        when (MainActivity.currentGen) {
//            1 -> getFirstGen()
//            2 -> getSecondGen()
//            3 -> getThirdGen()
//            4 -> getForthGen()
//        }
        Log.d("DO-WORK", "FINISHED")
        return Result.success()
    }

    private fun downloadPokemon(gen: Int) {
        val range = when (gen) {
            1 -> 1..151
            2 -> 152..251
            3 -> 252..386
            4 -> 387..494
            else -> 1..151
        }
        CoroutineScope(Dispatchers.Main).launch {
            for (i in range) {
                with (App.pokemonApi.fetchPokemonById("$i")) {
                    pokemonManager.savePokemon(this)?.let { pokemon ->
                        MainActivity.setPokemon(pokemon)
                    }
                    Log.d("SAVING", "$i: ${this?.name}")
                }
            }
        }
    }


//    private fun getFirstGen() {
//        CoroutineScope(Dispatchers.Main).launch {
//                for (i in 1..App.NUM_POKEMON) {
//                    with (App.pokemonApi.fetchPokemonById("$i")) {
//                        pokemonManager.savePokemon(this)?.let {pokemon ->
//                            MainActivity.setPokemon(pokemon)
//                        }
//                        Log.d("SAVING", "$i: ${this?.name}")
//                    }
//                }
//        }
//    }
//
//    private fun getSecondGen() {
//        CoroutineScope(Dispatchers.Main).launch {
//            for (i in 152..251) {
//                with (App.pokemonApi.fetchPokemonById("$i")) {
//                    pokemonManager.savePokemon(this)?.let {pokemon ->
//                        MainActivity.setPokemon(pokemon)
//                    }
//                    Log.d("SAVING", "$i: ${this?.name}")
//                }
//            }
//        }
//    }
//
//    private fun getThirdGen() {
//        CoroutineScope(Dispatchers.Main).launch {
//            for (i in 252..386) {
//                with (App.pokemonApi.fetchPokemonById("$i")) {
//                    pokemonManager.savePokemon(this)?.let {pokemon ->
//                        MainActivity.setPokemon(pokemon)
//                    }
//                    Log.d("SAVING", "$i: ${this?.name}")
//                }
//            }
//        }
//    }
//
//    private fun getForthGen() {
//        CoroutineScope(Dispatchers.Main).launch {
//            for (i in 387..494) {
//                with (App.pokemonApi.fetchPokemonById("$i")) {
//                    pokemonManager.savePokemon(this)?.let {pokemon ->
//                        MainActivity.setPokemon(pokemon)
//                    }
//                    Log.d("SAVING", "$i: ${this?.name}")
//                }
//            }
//        }
//    }





}