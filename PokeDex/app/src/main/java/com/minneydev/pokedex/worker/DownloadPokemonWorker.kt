package com.minneydev.pokedex.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.minneydev.pokedex.App
import com.minneydev.pokedex.MainActivity
import com.minneydev.pokedex.util.PokemonManager
import kotlinx.coroutines.*

class DownloadPokemonWorker(context: Context, workerParameters: WorkerParameters) :
                    Worker(context, workerParameters) {

    private val pokemonManager by lazy { PokemonManager() }

    override fun doWork(): Result {
        getFirstGen()
        Log.d("DO-WORK", "FINISHED")
        return Result.success()
    }

    private fun getFirstGen() {
        CoroutineScope(Dispatchers.Main).launch {
                for (i in 1..App.NUM_POKEMON) {
                    with (App.pokemonApi.fetchPokemonById("$i")) {
                        pokemonManager.savePokemon(this)?.let {pokemon ->
                            MainActivity.setPokemon(pokemon)
                        }
                        Log.d("SAVING", "$i: ${this?.name}")
                    }
                }
        }
    }



}