package com.minneydev.pokedex.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.minneydev.pokedex.App
import com.minneydev.pokedex.MainActivity
import com.minneydev.pokedex.util.PokemonManager
import com.minneydev.pokedex.util.PokemonManager.Companion.currentGen
import kotlinx.coroutines.*

class DownloadPokemonWorker(context: Context, workerParameters: WorkerParameters) :
                    Worker(context, workerParameters) {

    private val pokemonManager by lazy { PokemonManager() }
    private val firstGenRange = 1..151
    private val secondGenRange = 152..251
    private val thirdGenRange = 252..386
    private val fourthGenRange = 387..494

    override fun doWork(): Result {
        currentGen.let {
            downloadPokemon(it)
            Result.success()
        }
        return Result.failure()
    }

    private fun downloadPokemon(gen: Int) {
        val range = when (gen) {
            1 -> firstGenRange
            2 -> secondGenRange
            3 -> thirdGenRange
            4 -> fourthGenRange
            else -> firstGenRange
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
}