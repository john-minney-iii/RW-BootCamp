package com.minneydev.pokedex.viewModel

import android.util.Log //Import just for the Logs to see pokemon Downloading
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData //Ok android Import
import androidx.lifecycle.ViewModel //Ok android import
import com.minneydev.pokedex.App
import com.minneydev.pokedex.MainActivity
import com.minneydev.pokedex.model.pokemon.Pokemon
import com.minneydev.pokedex.repository.PokemonRepository
import com.minneydev.pokedex.util.PokemonManager
import com.minneydev.pokedex.util.PokemonManager.Companion.currentGen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent

/**
 * ViewModel for [MainActivity]
 */

class PokemonViewModel : ViewModel(), KoinComponent {

    private val pokemonRepository by lazy { PokemonRepository() }
    private val pokemonManager by lazy { PokemonManager() }
    private val allPokemon = MutableLiveData<List<Pokemon>>()
    private val networkStatusChecker = App.networkStatusChecker

    init {
        configurePokemonList()
        pokemonManager.startPeriodicRefresh()
    }

    fun fetchPokemonList() : LiveData<List<Pokemon>> = allPokemon

    fun configurePokemonList() {
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

    fun fetchPokemon() {
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