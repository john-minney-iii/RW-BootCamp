package com.minneydev.pokedex

import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.minneydev.pokedex.App.Companion.NUM_POKEMON
import com.minneydev.pokedex.model.pokemon.Pokemon
import com.minneydev.pokedex.networking.NetworkStatusChecker
import com.minneydev.pokedex.ui.PokemonAdapter
import com.minneydev.pokedex.worker.DownloadPokemonWorker
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    companion object {
        private val adapter = PokemonAdapter()
        fun setPokemon(pokemon: Pokemon) { adapter.setPokemon(pokemon) }
    }
    private val networkStatusChecker by lazy {
        NetworkStatusChecker(this.getSystemService(ConnectivityManager::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pokemonRecyclerView.layoutManager = LinearLayoutManager(this)
        lifecycleScope.launch {
            val pokemonList: List<Pokemon> = App.pokemonDb.pokemonDao().getAllPokemon()
            pokemonList.forEach { Log.d("TEST", "$it") }
            if (pokemonList.isNotEmpty()) {
                withContext(Dispatchers.Main) { placeOnRecyclerView(pokemonList.toSet()) }
            }else {
                networkStatusChecker.performIfConnectedToInternet {
                    downloadPokemon()
                }
            }
        }
        pokemonRecyclerView.adapter = adapter

    }

    private fun showPokemonInDatabase() {
        lifecycleScope.launch {
            val pokemonList = App.pokemonDb.pokemonDao().getAllPokemon()
            withContext(Dispatchers.Main) { placeOnRecyclerView(pokemonList.toSet()) }
        }
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

        workManager.getWorkInfoByIdLiveData(downloadRequest.id).observe(this, Observer {
            if (it.state.isFinished) {
                showPokemonInDatabase()
            }
        })

    }

//    Menu Code ------------------------------------------------------------------------------------
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        this.menuInflater.inflate(R.menu.about_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.aboutMenu -> showAbout()
        }
        return true
    }

    private fun showAbout() {
        AlertDialog.Builder(this)
                .setTitle(R.string.about_title)
                .setMessage(R.string.about_message)
                .create().show()
    }

}