package com.minneydev.pokedex.model.pokemon

import com.squareup.moshi.Json

data class PokemonListCall(
    @field:Json(name = "count") val count: Int = 0,
    @field:Json(name = "next") val nextUrl: String = "",
    @field:Json(name = "previous") val previousUrl: String? = "",
    @field:Json(name = "results") val listOfPokemon: List<ApiPokemon>
)