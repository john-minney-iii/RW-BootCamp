package com.minneydev.pokedex.model

import com.squareup.moshi.Json

data class SpriteSet (
    @field:Json(name = "front_default") val frontDefault: String
)