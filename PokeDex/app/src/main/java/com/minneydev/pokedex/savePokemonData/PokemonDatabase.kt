package com.minneydev.pokedex.savePokemonData

import androidx.room.Database
import androidx.room.RoomDatabase
import com.minneydev.pokedex.model.pokemon.Pokemon

@Database(entities = [Pokemon::class], version = 1, exportSchema = false)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}