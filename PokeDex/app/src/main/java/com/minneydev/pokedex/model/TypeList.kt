package com.minneydev.pokedex.model

import com.squareup.moshi.Json

class TypeList(
    @field:Json(name="type") val type: MainType
)


class MainType(
    @field:Json(name="name") val name: String)