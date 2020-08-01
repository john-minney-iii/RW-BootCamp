package com.minneydev.pokedex.util


import androidx.work.*
import com.minneydev.pokedex.App
import com.minneydev.pokedex.worker.DownloadPokemonWorker
import com.minneydev.pokedex.worker.RefreshPokemonWorker
import java.util.concurrent.TimeUnit

/**
 * Manager to start up the Workers and hold the Current Generation
 */

class PokemonManager {

    //I know the DOWNLOAD_MANAGER and REFRESH_MANAGER don't need to be in the companion, but
    //It was making me put them in there so I could keep them as a const.
    companion object {
        const val DOWNLOAD_WORKER = "DOWNLOAD"
        const val REFRESH_WORKER = "REFRESH"
        var currentGen = 1
    }

    /*
        I know you said that these could be split into more methods, but I didn't like having
        a method just to call another method. I like how they are here, I felt like it was
        just overkill to make another method is all.
     */
    fun startPeriodicRefresh() {
        val refreshManager = WorkManager.getInstance(App.getAppContext())
        refreshManager.enqueueUniquePeriodicWork(REFRESH_WORKER,
            ExistingPeriodicWorkPolicy.REPLACE, buildPeriodicRequest(60))
    }

    fun downloadPokemon() {
        val workManager = WorkManager.getInstance(App.getAppContext())
        workManager.enqueueUniqueWork(DOWNLOAD_WORKER,
            ExistingWorkPolicy.REPLACE, buildDownloadRequest())
    }

    private fun buildConstraints() : Constraints {
        return Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiresStorageNotLow(true)
            .setRequiredNetworkType(NetworkType.NOT_ROAMING)
            .build()
    }

    private fun buildPeriodicRequest(period: Long) : PeriodicWorkRequest {
        return PeriodicWorkRequestBuilder<RefreshPokemonWorker>(
            period, TimeUnit.MINUTES, //Minimum of 15 min apparently
            period/2, TimeUnit.MINUTES) //So then it doesn't run on the initial download
            .setConstraints(buildConstraints())
            .build()
    }

    private fun buildDownloadRequest() : OneTimeWorkRequest {
        return OneTimeWorkRequestBuilder<DownloadPokemonWorker>()
            .setConstraints(buildConstraints())
            .build()
    }

}
