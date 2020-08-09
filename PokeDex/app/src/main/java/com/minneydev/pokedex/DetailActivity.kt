package com.minneydev.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.minneydev.pokedex.model.pokemon.Pokemon
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.pokemon_card.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val pokemon = intent.getSerializableExtra("POKEMON") as? Pokemon
        if (pokemon != null) {
            Toast.makeText(applicationContext,pokemon.name, Toast.LENGTH_SHORT).show()
            initView(pokemon)
            initBackgroundColor(pokemon.type)
        }
    }

    private fun initBackgroundColor(type: String) {
        detailCardView.setCardBackgroundColor(
            ContextCompat.getColor(this, getTypeColor(type))
        )
    }

    /*
    pokemonLL.setBackgroundColor(
            ContextCompat.getColor(App.getAppContext(), typeColor)
        )
     */

    private fun initView(pokemon: Pokemon) {
        pokemonNameTextView.text = pokemon.name.capitalize()
        initSprites(pokemon)
    }

    private fun initSprites(pokemon: Pokemon) {
        Picasso.get().load(pokemon.front_sprite_url)
            .resize(500,500).into(frontSprite)
        Picasso.get().load(pokemon.back_sprite_url)
            .resize(500,500).into(backSprite)
        Picasso.get().load(pokemon.front_shiny_url)
            .resize(500,500).into(frontShinySprite)
        Picasso.get().load(pokemon.back_shiny_url)
            .resize(500,500).into(backShinySprite)
    }

    private fun getTypeColor(type: String) : Int {
        return when (type) {
            "bug" -> R.color.bugType
            "dragon" -> R.color.dragonType
            "electric" -> R.color.electricType
            "fighting" -> R.color.fightingType
            "fire" -> R.color.fireType
            "flying" -> R.color.flyingType
            "ghost" -> R.color.ghostType
            "grass" -> R.color.grassType
            "ground" -> R.color.groundType
            "ice" -> R.color.iceType
            "normal" -> R.color.normalType
            "poison" -> R.color.poisonType
            "psychic" -> R.color.psychicType
            "rock" -> R.color.rockType
            "water" -> R.color.waterType
            "steel" -> R.color.steelType
            "dark" -> R.color.darkType
            "fairy" -> R.color.fairyType
            else -> R.color.normalType
        }
    }

}