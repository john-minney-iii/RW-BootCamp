package com.minneydev.apiassignment

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.minneydev.apiassignment.models.pokemon.ApiPokemon
import com.minneydev.apiassignment.models.pokemon.Pokemon
import com.minneydev.apiassignment.networking.NetworkStatusChecker
import com.minneydev.apiassignment.ui.PokemonAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    companion object {
        lateinit var mainContext: Context
        private const val NUM_POKEMON = 151
    }

    private val adapter = PokemonAdapter()
    private val networkStatusChecker by lazy {
        NetworkStatusChecker(this.getSystemService(ConnectivityManager::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pokemonRecyclerView.layoutManager = LinearLayoutManager(this)
        mainContext = this

        CoroutineScope(Dispatchers.IO).launch {//Change this to lifecycleScope?
            val pokemonList: List<Pokemon> = App.pokemonDb.pokemonDao().getAllPokemon()
            if (pokemonList.isEmpty() || pokemonList.size < NUM_POKEMON || pokemonList.size > NUM_POKEMON) {
                networkStatusChecker.performIfConnectedToInternet {
                    getFirstGen()
                }
            }else {
                withContext(Dispatchers.Main) { placeOnRecyclerView(pokemonList.toSet()) }
            }
        }

        pokemonRecyclerView.adapter = adapter

    }

    //Figure out how to fetch without doing 151 api calls.
    private suspend fun getFirstGen() {
        for (i in 1..NUM_POKEMON) {
            onPokemonReceived(App.pokemonApi.fetchPokemonById("$i"))
        }
    }

    private fun onPokemonReceived(pokemon: ApiPokemon?) {
        if (pokemon != null) {
            val tempPokemon = savePokemon(pokemon)
            // Why did I have savePokemon on the UiThread. No wonder why it was loading slow
            runOnUiThread { adapter.setPokemon(tempPokemon) }
        }
    }

    private fun placeOnRecyclerView(pokemon: Set<Pokemon>) {
        pokemon.forEach {
            adapter.setPokemon(it)
        }
    }

    private fun savePokemon(pokemon: ApiPokemon?) : Pokemon? {
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