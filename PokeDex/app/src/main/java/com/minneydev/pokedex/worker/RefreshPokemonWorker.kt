package com.minneydev.pokedex.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.minneydev.pokedex.App
import com.minneydev.pokedex.MainActivity
import com.minneydev.pokedex.util.PokemonManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RefreshPokemonWorker(context: Context, workerParameters: WorkerParameters) :
                           Worker(context, workerParameters) {

    private val pokemonManager by lazy { PokemonManager() }

    override fun doWork(): Result {
        nukeDatabase()
        reDownloadPokemon(MainActivity.currentGen)
        return Result.success()
    }

    private fun nukeDatabase() {
        CoroutineScope(Dispatchers.IO).launch {
            App.pokemonDb.pokemonDao().nukeTable()
        }
    }

    private fun reDownloadPokemon(gen: Int) {
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
                    Log.d("PERIODIC", "$i: ${this?.name}")
                }
            }
        }
    }

}