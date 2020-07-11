package com.minneydev.apiassignment.models

import com.squareup.moshi.Json

data class Pokemon(
    @field:Json(name="id") val id: String?,
    @field:Json(name="name") val name: String?)
