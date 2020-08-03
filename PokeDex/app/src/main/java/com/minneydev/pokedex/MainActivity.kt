package com.minneydev.pokedex

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.minneydev.pokedex.model.pokemon.Pokemon
import com.minneydev.pokedex.ui.PokemonAdapter
import com.minneydev.pokedex.viewModel.PokemonViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private val adapter = PokemonAdapter()
        //The Adapter is in the CompObj just so the setPokemon() will work
        fun setPokemon(pokemon: Pokemon) { adapter.setPokemon(pokemon) }
        fun clearRecyclerView() { adapter.clear() }
        fun refreshToast() {
            Toast.makeText(App.getAppContext(), R.string.refresh_toast, Toast.LENGTH_SHORT).show()
        }
    }

    lateinit var pokemonViewModel: PokemonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pokemonViewModel = ViewModelProviders.of(this).get(PokemonViewModel::class.java)
        pokemonRecyclerView.layoutManager = LinearLayoutManager(this)
        observePokemon()
        pokemonRecyclerView.adapter = adapter
    }

    /**
    Observes Pokemon List in [PokemonViewModel]
     */
    private fun observePokemon() {
        pokemonViewModel.fetchPokemonList().let {
            it.observe(this, Observer { pokemonList ->
                putOnRecyclerView(pokemonList)
            })
        }

    }

    /**
    Code to change [R.id.pokemonRecyclerView]
     */
    private fun putOnRecyclerView(pokemon: List<Pokemon>) {
        pokemon.forEach {
            adapter.setPokemon(it)
        }
    }

    /**
     * Code to change Pokemon Generation
     */
    private fun showGeneration(gen: Int) {
        clearRecyclerView()
        pokemonViewModel.changeGeneration(gen)
        generationChangeToast(gen)
    }

    private fun generationChangeToast(gen: Int) {
        Toast.makeText(applicationContext,getString(R.string.gen_change_toast, gen.toString()),Toast.LENGTH_SHORT).show()
    }

    /**
     * Code for [R.menu.menu]
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        this.menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.aboutMenu -> showAbout()
            R.id.genOne -> showGeneration(1)
            R.id.genTwo -> showGeneration(2)
            R.id.genThree -> showGeneration(3)
            R.id.genFour -> showGeneration(4)
        }
        return true
    }

    private fun showAbout() {
        AlertDialog.Builder(this)
                .setTitle(R.string.about_title)
                .setMessage(R.string.about_message)
                .create().show()
    }

}