package com.minneydev.pokedex.viewModel

import org.junit.Before
import org.junit.Test

class PokemonViewModelTest {

    private lateinit var pokemonViewModel: PokemonViewModel


    @Before
    fun setup() {
        pokemonViewModel = PokemonViewModel()
    }

    @Test
    fun testPokemonFetchList() {
        pokemonViewModel.fetchPokemonList()
    }

    @Test
    fun testConfigPokemon() {
        pokemonViewModel.configurePokemonList()
    }

    @Test
    fun testPokemonFetch() {
        pokemonViewModel.fetchPokemon()
    }


}