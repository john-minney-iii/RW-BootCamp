package com.minneydev.apiassignment.models

import com.squareup.moshi.Json


data class Type(
    @field:Json(name = "id") val id: String?,
    @field:Json(name = "name") val name: String?) {

    override fun toString(): String {
        return "Id: $id, Name: $name"
    }


}