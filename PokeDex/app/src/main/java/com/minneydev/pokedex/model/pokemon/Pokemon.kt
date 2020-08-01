package com.minneydev.pokedex.model.pokemon

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Pokemon Being Saved into [PokemonDatabase]
 */
@Entity(tableName = "pokemon_table")
data class Pokemon(
    @PrimaryKey val id: String = "",
    val name: String = "",
    val sprite_url: String = "",
    val type: String = "")
