package com.minneydev.pokedex.model

import com.squareup.moshi.Json

/**
 * Fetches Pokemon Type from Api
 * I know this is a weird way of doing it, but that is how the API was set up.
 */

class TypeList(
    @field:Json(name="type") val type: MainType
)


class MainType(
    @field:Json(name="name") val name: String)