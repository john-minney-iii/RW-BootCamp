package com.minneydev.apiassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.minneydev.apiassignment.models.pokemon.ApiPokemon
import com.minneydev.apiassignment.models.pokemon.Pokemon
import com.minneydev.apiassignment.ui.PokemonAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private val adapter = PokemonAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pokemonRecyclerView.layoutManager = LinearLayoutManager(this)

        CoroutineScope(Dispatchers.IO).launch {
            with (App.pokemonDb.pokemonDao().getAllPokemon()) {
                if (this.isEmpty()) { getFirstGen() }
                else { runOnUiThread { placeOnRecyclerView(this.toSet()) } }
            }
        }

        pokemonRecyclerView.adapter = adapter

    }

    private suspend fun getFirstGen() {
        for (i in 1..151) {
            onPokemonReceived(App.pokemonApi.fetchPokemonById("$i"))
        }
    }

    private fun onPokemonReceived(pokemon: ApiPokemon?) {
        if (pokemon != null) {
            runOnUiThread { adapter.setPokemon(savePokemon(pokemon)) }
        }
    }

    private fun placeOnRecyclerView(pokemon: Set<Pokemon>) {
        pokemon.forEach {
            adapter.setPokemon(it)
        }
    }

    private fun savePokemon(pokemon: ApiPokemon?) : Pokemon? {
        val tempPokemon = pokemon?.let {
            Pokemon(id = it.id!!, name = it.name!!, sprite_url = it.sprites.frontDefault,
                    type = it.types[0].type.name)
        }
        if (tempPokemon != null) {
            CoroutineScope(Dispatchers.IO).launch {
                App.pokemonDb.pokemonDao().insertPokemon(tempPokemon)
            }
        }
        return tempPokemon
    }


}