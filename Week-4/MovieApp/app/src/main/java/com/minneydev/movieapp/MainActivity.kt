package com.minneydev.movieapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import com.minneydev.movieapp.data.Movie
import com.minneydev.movieapp.ui.MainAdapter
import kotlinx.android.synthetic.main.activity_main.*

//A few of the movies Jen put in her's are some of my favorites lol.

class MainActivity : AppCompatActivity(), MainAdapter.MovieClickListener {

    companion object {
        const val INTENT_MOVIE_KEY = "movie"
    }

    private val spanCount = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainRecyclerView.layoutManager = GridLayoutManager(this, spanCount)
        mainRecyclerView.adapter = MainAdapter(this)

        mainRecyclerView.setOnClickListener {

        }
    }

    private fun showMovieDetail(movie: Movie) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(INTENT_MOVIE_KEY, movie)
        startActivity(intent)
    }

    override fun movieClicked(movie: Movie) {
        showMovieDetail(movie)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.about, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.aboutMenu) {
            showInfo()
        }
        return true
    }

    private fun showInfo() {
        AlertDialog.Builder(this)
            .setTitle(R.string.alert_title)
            .setMessage(R.string.alert_message)
            .create().show()
    }

}