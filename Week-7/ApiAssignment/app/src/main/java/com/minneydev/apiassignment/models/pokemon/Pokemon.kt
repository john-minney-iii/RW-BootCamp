package com.minneydev.apiassignment.models.pokemon

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_table")
data class Pokemon(
    @PrimaryKey val id: String = "",
    val name: String = "",
    val sprite_url: String = "",
    val type: String = "")
