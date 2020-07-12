package com.minneydev.apiassignment.models

import com.squareup.moshi.Json

class Type(
    @field:Json(name="type") val type: Type2)


class Type2(
    @field:Json(name="name") val name: String)