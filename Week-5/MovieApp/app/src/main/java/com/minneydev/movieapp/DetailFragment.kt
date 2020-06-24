package com.minneydev.movieapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.minneydev.movieapp.data.Movie
import com.minneydev.movieapp.data.getMoviesArray
import com.minneydev.movieapp.ui.DetailAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class DetailFragment : Fragment() {

    companion object {
        lateinit var movie: Movie

        private val ARG_MOVIE = "movie"

        fun newInstance(movie: Movie): DetailFragment {
            val bundle = Bundle()
            bundle.putParcelable(ARG_MOVIE, movie)
            val fragment = DetailFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val args = DetailFragmentArgs.fromBundle(it)
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
//            title = movie.title
            cardTitle.text = movie.title
            Picasso.get()
                .load(movie.bannerUrl)
                .placeholder(R.drawable.image_placeholder)
                .fit()
                .into(moviePoster)
        }
    }
}
