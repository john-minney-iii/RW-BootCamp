package com.minneydev.pokedex.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.minneydev.pokedex.MainActivity
import com.minneydev.pokedex.repository.PokemonRepository
import com.minneydev.pokedex.util.PokemonManager.Companion.currentGen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class DownloadPokemonWorker(context: Context, workerParameters: WorkerParameters) :
                    Worker(context, workerParameters), KoinComponent {

//    private val pokemonRepository by lazy { PokemonRepository() }
    private val pokemonRepository: PokemonRepository by inject()
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

    override fun onStopped() {
        super.onStopped()
        MainActivity.clearRecyclerView()
        Log.d("WORKER-CANCELED", "Maybe?")
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
                with (pokemonRepository.fetchPokemonById(i.toString())) {
                    pokemonRepository.savePokemon(this)?.let { pokemon ->
                        MainActivity.setPokemon(pokemon)
                    }
                }
            }
        }
    }
}