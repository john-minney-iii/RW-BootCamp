package com.minneydev.apiassignment.models.pokemon

import com.minneydev.apiassignment.models.SpriteSet
import com.minneydev.apiassignment.models.Type
import com.squareup.moshi.Json

data class ApiPokemon(
    @field:Json(name="id") val id: String = "",
    @field:Json(name="name") val name: String = "",
    @field:Json(name="sprites") val sprites: SpriteSet,
    @field:Json(name="types") val types: List<Type>)