package com.minneydev.movieapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.minneydev.movieapp.R
import com.minneydev.movieapp.data.Movie
import com.minneydev.movieapp.data.getMoviesArray
import com.minneydev.movieapp.savingMovieData.MovieViewModel
import com.minneydev.movieapp.ui.MovieAdapter
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : Fragment(), MovieAdapter.MovieClickListener {

    private val spanCount = 2

    private lateinit var movieViewModel: MovieViewModel

    override fun movieClicked(movie: Movie) {
        showMovieDetail(movie)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainRecyclerView.layoutManager = GridLayoutManager(activity, spanCount)
        val adapter = MovieAdapter(this)
        mainRecyclerView.adapter = adapter
        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        movieViewModel.allMovies.observe(viewLifecycleOwner, Observer { movies ->
            movies?.let { adapter.setMovies(it) }
        })
    }

    private fun showMovieDetail(movie: Movie) {
        view?.let {
            val action =
                MovieFragmentDirections.actionMovieFragmentToDetailFragment(
                    movie.title
                )
            it.findNavController().navigate(action)
        }
    }

    private fun addMovies(movieList: List<Movie>) {
        movieList.forEach {
            movieViewModel.insert(it)
        }
    }

}
