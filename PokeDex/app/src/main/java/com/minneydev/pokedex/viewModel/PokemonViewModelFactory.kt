package com.minneydev.pokedex.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.minneydev.pokedex.networking.NetworkStatusChecker
import com.minneydev.pokedex.repository.PokemonRepository
import com.minneydev.pokedex.util.PokemonManager

class PokemonViewModelFactory(
    private val pokemonRepository: PokemonRepository,
    private val pokemonManager: PokemonManager,
    private val networkStatusChecker: NetworkStatusChecker
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T: ViewModel> create(modelClass:Class<T>): T {
        return PokemonViewModel(pokemonRepository, pokemonManager, networkStatusChecker) as T
    }




}