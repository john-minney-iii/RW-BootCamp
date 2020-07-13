package com.minneydev.apiassignment.savePokemonData

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.minneydev.apiassignment.models.pokemon.Pokemon

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPokemon(pokemon: Pokemon)

    @Query("SELECT * FROM pokemon_table")
    fun getAllPokemon(): List<Pokemon>


}