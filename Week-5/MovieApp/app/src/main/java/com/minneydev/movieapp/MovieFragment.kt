package com.minneydev.movieapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.minneydev.movieapp.data.Movie
import com.minneydev.movieapp.ui.MovieAdapter
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : Fragment(), MovieAdapter.MovieClickListener {

    private val spanCount = 2

    override fun movieClicked(movie: Movie) {
        showMovieDetail(movie)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainRecyclerView.layoutManager = GridLayoutManager(activity, spanCount)
        mainRecyclerView.adapter = MovieAdapter(this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    private fun showMovieDetail(movie: Movie) {
        view?.let {
            val action = MovieFragmentDirections.actionMovieFragmentToDetailFragment(movie.title)
            it.findNavController().navigate(action)
        }
    }

    interface OnFragmentInteractionListener {
        fun onMovieClicked(movie: Movie)
    }

    companion object {
        fun newInstance(): MovieFragment {
            return MovieFragment()
        }
    }
}

/*
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
 */