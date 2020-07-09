package com.minneydev.movieapp.movieDetailFragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.minneydev.movieapp.R
import com.minneydev.movieapp.data.Movie
import com.minneydev.movieapp.savingMovieData.MovieViewModel
import com.minneydev.movieapp.movieDetailFragment.ui.DetailAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class DetailFragment : Fragment() {

    private var detailMovie: Movie? = null

    private lateinit var movieViewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            detailRecyclerView.layoutManager = LinearLayoutManager(it)
            arguments?.let {bundle ->
                movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
                val args =
                    DetailFragmentArgs.fromBundle(
                        bundle
                    ).movieString
                lifecycleScope.launch {
                    initFragment(movieViewModel.getByTitle(args))
                }
            }

            searchMovieFab.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(detailMovie?.imdb)
                startActivity(intent)
            }

        }
    }

    private fun initFragment(movie: Movie?) {
        detailMovie = movie
        displayMovie(detailMovie)
        detailRecyclerView.adapter =
            detailMovie?.let { it -> DetailAdapter(it) }
    }

    private fun displayMovie(movie: Movie?) {
        if (movie != null) {
            movieTitle.text = movie.title
            Picasso.get()
                .load(movie.bannerUrl)
                .placeholder(R.drawable.image_placeholder)
                .fit()
                .into(moviePoster)
        }
    }
}
