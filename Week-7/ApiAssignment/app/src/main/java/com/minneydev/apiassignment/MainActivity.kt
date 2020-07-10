package com.minneydev.apiassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.minneydev.apiassignment.networking.PokemonApi
import kotlinx.android.synthetic.main.activity_main.*

const val BASE_URL = "https://pokeapi.co/api/v2/"

class MainActivity : AppCompatActivity() {

    private val pokemonApi = PokemonApi()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pokemonApi.fetchFirstGenPokemon()

    }

    private fun initRecyclerView() {
        pokemonRecyclerView.layoutManager = GridLayoutManager(this,2)
    }
}