package com.minneydev.pokedex

import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
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
        var currentGen: MutableLiveData<Int> = MutableLiveData()
        private val adapter = PokemonAdapter()
        fun setPokemon(pokemon: Pokemon) { adapter.setPokemon(pokemon) }

    }
    private val networkStatusChecker by lazy {
        NetworkStatusChecker(this.getSystemService(ConnectivityManager::class.java))
    }
    private val pokemonManager by lazy { PokemonManager() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        currentGen.value = 1
        pokemonRecyclerView.layoutManager = LinearLayoutManager(this)
        lifecycleScope.launch {
            val pokemonList: List<Pokemon> = App.pokemonDb.pokemonDao().getAllPokemon()
            pokemonList.forEach { Log.d(App.TAG, "$it") }
            if (pokemonList.isNotEmpty()) {
                withContext(Dispatchers.Main) { placeOnRecyclerView(pokemonList.toSet()) }
            }else {
                networkStatusChecker.performIfConnectedToInternet {
                    Toast.makeText(applicationContext,getString(R.string.downloading_toast), Toast.LENGTH_SHORT).show()
                    pokemonManager.downloadPokemon()
                }
            }
        }
        pokemonRecyclerView.adapter = adapter
        pokemonManager.startPeriodicRefresh()
    }

    private fun clearRecyclerView() {
        adapter.clear()
    }

    private fun placeOnRecyclerView(pokemon: Set<Pokemon>) {
        pokemon.forEach {
            adapter.setPokemon(it)
        }
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
        currentGen.value = gen
        lifecycleScope.launch {App.pokemonDb.pokemonDao().nukeTable()}
        clearRecyclerView()
        pokemonManager.downloadPokemon()
        Toast.makeText(applicationContext,getString(R.string.gen_change_toast, gen.toString()),Toast.LENGTH_SHORT).show()
    }

    private fun showAbout() {
        AlertDialog.Builder(this)
                .setTitle(R.string.about_title)
                .setMessage(R.string.about_message)
                .create().show()
    }

}