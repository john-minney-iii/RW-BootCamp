package com.minneydev.pokedex.savePokemonData

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.minneydev.pokedex.model.pokemon.Pokemon

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPokemon(pokemon: Pokemon)

    @Query("SELECT * FROM pokemon_table")
    suspend fun getAllPokemon(): List<Pokemon>


}