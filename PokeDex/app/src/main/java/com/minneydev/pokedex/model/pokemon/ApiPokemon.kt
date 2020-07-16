package com.minneydev.pokedex.model.pokemon


import com.minneydev.pokedex.model.SpriteSet
import com.minneydev.pokedex.model.Type
import com.squareup.moshi.Json

data class ApiPokemon(
    @field:Json(name="id") val id: String = "",
    @field:Json(name="name") val name: String = "",
    @field:Json(name="sprites") val sprites: SpriteSet,
    @field:Json(name="types") val types: List<Type>)