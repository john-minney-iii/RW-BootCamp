package com.minneydev.pokedex.model

import com.squareup.moshi.Json

/**
 * Fetches 'Sprite Sheet' from Api
 */
data class SpriteSet (
    @field:Json(name = "front_default") val frontDefault: String
)