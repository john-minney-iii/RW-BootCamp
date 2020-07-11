package com.minneydev.apiassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.minneydev.apiassignment.models.Pokemon
import com.minneydev.apiassignment.ui.PokemonAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import okhttp3.internal.wait

class MainActivity : AppCompatActivity() {

    private val setOfPokemon = mutableSetOf<Pokemon?>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pokemonRecyclerView.layoutManager = LinearLayoutManager(this)
        GlobalScope.launch(Dispatchers.IO) { getFirstGen() }
        pokemonRecyclerView.adapter = PokemonAdapter(setOfPokemon)

    }

    private suspend fun getFirstGen() {
        for (i in 1..151) {
            setOfPokemon.add(App.pokemonApi.fetchPokemonById("$i"))
            refreshList()
        }
    }


    private fun refreshList() {
        runOnUiThread { pokemonRecyclerView.adapter?.notifyDataSetChanged() }
    }

}