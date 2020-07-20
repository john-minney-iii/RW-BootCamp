package com.minneydev.pokedex.util

import androidx.work.*
import com.minneydev.pokedex.App
import com.minneydev.pokedex.model.pokemon.ApiPokemon
import com.minneydev.pokedex.model.pokemon.Pokemon
import com.minneydev.pokedex.worker.DownloadPokemonWorker
import com.minneydev.pokedex.worker.RefreshPokemonWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class PokemonManager {

    fun savePokemon(pokemon: ApiPokemon?) : Pokemon? {
        val tempPokemon = pokemon?.let {
            Pokemon(id = it.id, name = it.name, sprite_url = it.sprites.frontDefault,
                type = it.types[0].type.name)
        }
        if (tempPokemon != null) {
            CoroutineScope(Dispatchers.IO).launch {
                App.pokemonDb.pokemonDao().insertPokemon(tempPokemon)
            }
        }
        return tempPokemon
    }

    fun getFirstId(gen: Int) : String {
        return when (gen) {
            1 -> "1"
            2 -> "152"
            3 -> "252"
            4 -> "387"
            else -> "0"
        }
    }

    fun startPeriodicRefresh() {
        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiredNetworkType(NetworkType.NOT_ROAMING)
            .setRequiresStorageNotLow(true)
            .build()

        val periodicRefresh = PeriodicWorkRequestBuilder<RefreshPokemonWorker>(
            1, TimeUnit.HOURS, //Minimum of 15 min apparently
        30, TimeUnit.MINUTES) //So then it doesn't run on the initial download
            .setConstraints(constraints)
            .build()

        val refreshManager = WorkManager.getInstance(App.getAppContext())
        refreshManager.enqueueUniquePeriodicWork("REFRESH",
            ExistingPeriodicWorkPolicy.REPLACE, periodicRefresh)

    }

    fun downloadPokemon() {
        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiresStorageNotLow(true)
            .setRequiredNetworkType(NetworkType.NOT_ROAMING)
            .build()

        val downloadRequest = OneTimeWorkRequestBuilder<DownloadPokemonWorker>()
            .setConstraints(constraints)
            .build()

        val workManager = WorkManager.getInstance(App.getAppContext())
        workManager.enqueueUniqueWork("DOWNLOAD",
            ExistingWorkPolicy.APPEND, downloadRequest)
    }

}
