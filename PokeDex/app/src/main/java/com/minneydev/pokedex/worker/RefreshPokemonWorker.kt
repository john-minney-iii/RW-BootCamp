package com.minneydev.pokedex.worker

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.minneydev.pokedex.MainActivity
import com.minneydev.pokedex.R
import com.minneydev.pokedex.repository.PokemonRepository
import com.minneydev.pokedex.util.PokemonManager.Companion.currentGen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class RefreshPokemonWorker(context: Context, workerParameters: WorkerParameters) :
                           Worker(context, workerParameters), KoinComponent {

    private val pokemonRepository: PokemonRepository by inject()

    override fun doWork(): Result {
        currentGen.let {
            MainActivity.refreshToast()
            pokemonRepository.nukeDatabase()
            reDownloadPokemon(it)
            Result.success()
        }
        return Result.failure()
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
                with (pokemonRepository.fetchPokemonById(i.toString())) {
                    pokemonRepository.savePokemon(this)?.let { pokemon ->
                        MainActivity.setPokemon(pokemon)
                    }
                    Log.d("PERIODIC", "$i: ${this?.name}")
                }
            }
        }
    }

}