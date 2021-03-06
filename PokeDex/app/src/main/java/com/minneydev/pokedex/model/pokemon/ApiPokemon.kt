package com.minneydev.pokedex.model.pokemon


import com.minneydev.pokedex.model.SpriteSet
import com.minneydev.pokedex.model.TypeList
import com.squareup.moshi.Json

/**
 * Model that is fetched from the [networking.PokemonApi]
 */

data class ApiPokemon(
    @field:Json(name="id") val id: String = "",
    @field:Json(name="name") val name: String = "",
    @field:Json(name="sprites") val sprites: SpriteSet,
    @field:Json(name="types") val types: List<TypeList>)

{

    fun mapToPokemon() : Pokemon {
        return Pokemon(id = id, name = name, front_sprite_url = sprites.frontDefault,
            back_sprite_url = sprites.backDefault, front_shiny_url = sprites.frontShiny,
            back_shiny_url = sprites.backShiny, type = types[0].type.name)
    }


}