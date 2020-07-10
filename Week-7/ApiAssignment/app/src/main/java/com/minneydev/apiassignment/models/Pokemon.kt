package com.minneydev.apiassignment.models

data class Pokemon(
    val name: String)

{

    override fun toString(): String {
        return "Name: $name"
    }



}