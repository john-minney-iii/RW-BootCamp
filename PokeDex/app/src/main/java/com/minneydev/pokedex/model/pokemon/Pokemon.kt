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
    val front_sprite_url: String = "",
    val back_sprite_url: String = "",
    val front_shiny_url: String = "",
    val back_shiny_url: String = "",
    val type: String = "")
