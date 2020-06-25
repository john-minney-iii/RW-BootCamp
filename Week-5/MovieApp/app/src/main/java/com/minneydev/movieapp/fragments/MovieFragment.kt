package com.minneydev.movieapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.minneydev.movieapp.R
import com.minneydev.movieapp.data.Movie
import com.minneydev.movieapp.ui.MovieAdapter
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : Fragment(), MovieAdapter.MovieClickListener {

    private val spanCount = 2

    override fun movieClicked(movie: Movie) {
        showMovieDetail(movie)
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

    private fun showMovieDetail(movie: Movie) {
        view?.let {
            val action =
                MovieFragmentDirections.actionMovieFragmentToDetailFragment(
                    movie.title
                )
            it.findNavController().navigate(action)
        }
    }

}
