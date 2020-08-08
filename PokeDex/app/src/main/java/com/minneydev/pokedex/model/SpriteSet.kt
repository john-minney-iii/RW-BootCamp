package com.minneydev.pokedex.model

import com.squareup.moshi.Json

/**
 * Fetches 'Sprite Sheet' from Api
 */
data class SpriteSet (
    @field:Json(name = "front_default") val frontDefault: String,
    @field:Json(name = "back_default") val backDefault: String,
    @field:Json(name = "front_shiny") val frontShiny: String,
    @field:Json(name = "back_shiny") val backShiny: String
)