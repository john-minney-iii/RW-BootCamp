package com.minneydev.pokedex

import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.minneydev.pokedex.model.pokemon.Pokemon
import com.minneydev.pokedex.networking.NetworkStatusChecker
import com.minneydev.pokedex.ui.PokemonAdapter
import com.minneydev.pokedex.util.PokemonManager
import com.minneydev.pokedex.util.PokemonManager.Companion.currentGen
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
    private val pokemonManager by lazy { PokemonManager() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pokemonRecyclerView.layoutManager = LinearLayoutManager(this)
        configurePokemonList()
        pokemonRecyclerView.adapter = adapter
        pokemonManager.startPeriodicRefresh()
    }


    //On Create Code -------------------------------------------------------------------------------
    private fun configurePokemonList() {
        lifecycleScope.launch {
            val pokemonList: List<Pokemon> = App.pokemonDb.pokemonDao().getAllPokemon()
            pokemonList.forEach { Log.d(App.TAG, "$it") }
            if (!workWithPokemonList(pokemonList)) { fetchPokemon() }
        }
    }

    private suspend fun workWithPokemonList(pokemon: List<Pokemon>) : Boolean {
        if (pokemon.isNotEmpty()) {
            withContext(Dispatchers.Main) { placeOnRecyclerView(pokemon.toSet()) }
            return true
        }
        return false
    }

    private fun fetchPokemon() {
        networkStatusChecker.performIfConnectedToInternet {
            Toast.makeText(applicationContext,getString(R.string.downloading_toast), Toast.LENGTH_SHORT).show()
            pokemonManager.downloadPokemon()
        }
    }

    //RecyclerView Code ----------------------------------------------------------------------------
    private fun clearRecyclerView() {
        adapter.clear()
    }

    private fun placeOnRecyclerView(pokemon: Set<Pokemon>) {
        pokemon.forEach {
            adapter.setPokemon(it)
        }
    }

    //Generation Change ----------------------------------------------------------------------------

    private fun showGeneration(gen: Int) {
        currentGen = gen
        nukeDataBase()
        clearRecyclerView()
        downloadPokemon()
        generationChangeToast(gen)
    }

    private fun nukeDataBase() {
        lifecycleScope.launch {App.pokemonDb.pokemonDao().nukeTable()}
    }

    private fun downloadPokemon() {
        pokemonManager.downloadPokemon()
    }

    private fun generationChangeToast(gen: Int) {
        Toast.makeText(applicationContext,getString(R.string.gen_change_toast, gen.toString()),Toast.LENGTH_SHORT).show()

    }

    //Menu Code ------------------------------------------------------------------------------------
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

    private fun showAbout() {
        AlertDialog.Builder(this)
                .setTitle(R.string.about_title)
                .setMessage(R.string.about_message)
                .create().show()
    }

}