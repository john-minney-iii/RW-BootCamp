package com.minneydev.movieapp.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.minneydev.movieapp.R
import com.minneydev.movieapp.data.Movie
import com.minneydev.movieapp.data.getMoviesArray
import com.minneydev.movieapp.ui.DetailAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    companion object {
        lateinit var movie: Movie
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val args =
                DetailFragmentArgs.fromBundle(it)
            movie = getMoviesArray().filter { movie -> movie.title == args.movieString } [0]
        }

        activity?.let {
            displayMovie(movie)
            detailRecyclerView.layoutManager = LinearLayoutManager(it)
            detailRecyclerView.adapter = DetailAdapter()

            searchMovieFab.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(movie.imdb)
                startActivity(intent)
            }
        }
    }

    private fun displayMovie(movie: Movie?) {
        if (movie != null) {
            cardTitle.text = movie.title
            Picasso.get()
                .load(movie.bannerUrl)
                .placeholder(R.drawable.image_placeholder)
                .fit()
                .into(moviePoster)
        }
    }
}
