package com.minneydev.apiassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.minneydev.apiassignment.models.Pokemon
import com.minneydev.apiassignment.ui.PokemonAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private val adapter = PokemonAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pokemonRecyclerView.layoutManager = LinearLayoutManager(this)
        GlobalScope.launch(Dispatchers.IO) { getFirstGen() }
        pokemonRecyclerView.adapter = adapter

    }

    private suspend fun getFirstGen() {
        for (i in 1..151) {
            onPokemonReceived(App.pokemonApi.fetchPokemonById("$i"))
        }
    }

    private fun onPokemonReceived(pokemon: Pokemon?) {
        if (pokemon != null) {
            runOnUiThread { adapter.setPokemon(pokemon) }
        }
    }

}