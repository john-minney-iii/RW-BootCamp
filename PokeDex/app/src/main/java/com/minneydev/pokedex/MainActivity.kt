package com.minneydev.pokedex

import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.*
import com.minneydev.pokedex.model.pokemon.Pokemon
import com.minneydev.pokedex.networking.NetworkStatusChecker
import com.minneydev.pokedex.ui.PokemonAdapter
import com.minneydev.pokedex.util.PokemonManager
import com.minneydev.pokedex.worker.DownloadPokemonWorker
import com.minneydev.pokedex.worker.RefreshPokemonWorker
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    companion object {
        private val adapter = PokemonAdapter()
        var currentGen: Int = 1
        fun setPokemon(pokemon: Pokemon) { adapter.setPokemon(pokemon) }
    }

    private val networkStatusChecker by lazy {
        NetworkStatusChecker(this.getSystemService(ConnectivityManager::class.java))
    }
    private val pokemonManager by lazy { PokemonManager() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pokemonRecyclerView.layoutManager = LinearLayoutManager(this)
        lifecycleScope.launch {
            val pokemonList: List<Pokemon> = App.pokemonDb.pokemonDao().getAllPokemon()
            pokemonList.forEach { Log.d(App.TAG, "$it") }
            if (pokemonList.isNotEmpty()) {
                withContext(Dispatchers.Main) { placeOnRecyclerView(pokemonList.toSet()) }
            }else {
                networkStatusChecker.performIfConnectedToInternet {
                    downloadPokemon()
                }
            }
        }
        pokemonRecyclerView.adapter = adapter
        startPeriodicRefresh()
    }

    private fun showPokemonInDatabase() {
        lifecycleScope.launch {
            val pokemonList = App.pokemonDb.pokemonDao().getAllPokemon()
            withContext(Dispatchers.Main) { placeOnRecyclerView(pokemonList.toSet()) }
        }
    }

    private fun clearRecyclerView() {
        adapter.clear()
    }

    private fun placeOnRecyclerView(pokemon: Set<Pokemon>) {
        pokemon.forEach {
            adapter.setPokemon(it)
        }
    }

    private fun downloadPokemon() {
        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiresStorageNotLow(true)
            .setRequiredNetworkType(NetworkType.NOT_ROAMING)
            .build()

        val downloadRequest = OneTimeWorkRequestBuilder<DownloadPokemonWorker>()
            .setConstraints(constraints)
            .build()

        val workManager = WorkManager.getInstance(this)
        workManager.enqueue(downloadRequest)
    }

    private fun startPeriodicRefresh() {
        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiredNetworkType(NetworkType.NOT_ROAMING)
            .setRequiresStorageNotLow(true)
            .build()

        val periodicRefresh = PeriodicWorkRequestBuilder<RefreshPokemonWorker>(15, TimeUnit.HOURS)
            .setConstraints(constraints)
            .build()

        val refreshManager = WorkManager.getInstance(this)
        refreshManager.enqueueUniquePeriodicWork("REFRESH",
            ExistingPeriodicWorkPolicy.REPLACE, periodicRefresh)

    }


//    Menu Code ------------------------------------------------------------------------------------
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        this.menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.aboutMenu -> showAbout()
            R.id.genOne -> showGeneration(1)
            R.id.genTwo -> showGeneration(2)
            R.id.genThree -> showGeneration(3)
            R.id.genFour -> showGeneration(4)
        }
        return true
    }

    private fun showGeneration(gen: Int) {
        currentGen = gen
        clearRecyclerView()
        lifecycleScope.launch {App.pokemonDb.pokemonDao().nukeTable()}
        downloadPokemon()
        Toast.makeText(applicationContext,"Now Showing Gen $gen",Toast.LENGTH_SHORT).show()
    }

    private fun showAbout() {
        AlertDialog.Builder(this)
                .setTitle(R.string.about_title)
                .setMessage(R.string.about_message)
                .create().show()
    }

}