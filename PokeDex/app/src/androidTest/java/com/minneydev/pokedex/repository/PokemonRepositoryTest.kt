package com.minneydev.pokedex.repository

import com.minneydev.pokedex.model.MainType
import com.minneydev.pokedex.model.SpriteSet
import com.minneydev.pokedex.model.TypeList
import com.minneydev.pokedex.model.pokemon.ApiPokemon
import org.junit.Before
import org.junit.Test

class PokemonRepositoryTest {

    private lateinit var repository: PokemonRepository

    @Before
    fun setup() {
        repository = PokemonRepository()
    }


    @Test
    fun testSavePokemon() {
        val tempPokemon = ApiPokemon(id = "1", name = "Test", sprites = SpriteSet("test-url"),types = listOf(TypeList(
            MainType("Test Type"))))

        repository.savePokemon(tempPokemon)
    }

}