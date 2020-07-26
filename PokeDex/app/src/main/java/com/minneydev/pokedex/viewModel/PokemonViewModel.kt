package com.minneydev.pokedex.viewModel

import android.net.ConnectivityManager
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.minneydev.pokedex.App
import com.minneydev.pokedex.MainActivity
import com.minneydev.pokedex.model.pokemon.Pokemon
import com.minneydev.pokedex.networking.NetworkStatusChecker
import com.minneydev.pokedex.repository.PokemonRepository
import com.minneydev.pokedex.util.PokemonManager
import com.minneydev.pokedex.util.PokemonManager.Companion.currentGen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * ViewModel for [MainActivity]
 */

class PokemonViewModel : ViewModel() {

    private val networkStatusChecker by lazy {
        NetworkStatusChecker(App.getAppContext().getSystemService(ConnectivityManager::class.java))
    }
    private val pokemonRepository by lazy { PokemonRepository() }
    private val pokemonManager by lazy { PokemonManager() }
    private val allPokemon = MutableLiveData<List<Pokemon>>()

    init {
        configurePokemonList()
        pokemonManager.startPeriodicRefresh()
    }

    fun fetchPokemonList() : LiveData<List<Pokemon>> = allPokemon

    private fun configurePokemonList() {
        CoroutineScope(Dispatchers.Main).launch {
            val pokemonList: List<Pokemon> = pokemonRepository.fetchAllPokemon()
            pokemonList.forEach { Log.d(App.TAG, "$it") }
            if (pokemonList.isEmpty()) { fetchPokemon() }
            if (!workWithPokemonList(pokemonList)) { fetchPokemon() }
        }
    }

    private fun workWithPokemonList(pokemon: List<Pokemon>) : Boolean {
        if (pokemon.isNotEmpty()) {
            allPokemon.value = pokemon
            return true
        }
        return false
    }

    private fun fetchPokemon() {
        networkStatusChecker.performIfConnectedToInternet {
            pokemonManager.downloadPokemon()
        }
    }

    fun changeGeneration(gen: Int) {
        currentGen = gen
        nukeDatabase()
        allPokemon.value = emptyList()
        fetchPokemon()
    }

    private fun nukeDatabase() = pokemonRepository.nukeDatabase()

}