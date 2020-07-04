package com.minneydev.movieapp.movieGridFragment

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.minneydev.movieapp.R
import com.minneydev.movieapp.data.Movie
import com.minneydev.movieapp.fragments.MovieFragmentDirections
import com.minneydev.movieapp.manager.UserDataManager
import com.minneydev.movieapp.savingMovieData.MovieViewModel
import com.minneydev.movieapp.movieGridFragment.ui.MovieAdapter
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlin.system.exitProcess

class MovieFragment : Fragment(), MovieAdapter.MovieClickListener {

    private val spanCount = 2
    private lateinit var movieViewModel: MovieViewModel
    lateinit var userDataManager: UserDataManager

    override fun movieClicked(movie: Movie) {
        showMovieDetail(movie)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                userDataManager.logOutUser()
                activity?.let {
                    closeApp()
                }
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMovieGrid()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        userDataManager = UserDataManager(context)
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
    //I'll implement this in later when we are using networking. I think it would be cool to
    //add a search and add
    private fun addMovies(movieList: List<Movie>) {
        movieList.forEach {
            movieViewModel.insert(it)
        }
    }

    private fun closeApp() {
        exitProcess(0)
    }

    private fun initMovieGrid() {
        mainRecyclerView.layoutManager = GridLayoutManager(activity, spanCount)
        val adapter =
            MovieAdapter(this)
        mainRecyclerView.adapter = adapter

        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        movieViewModel.allMovies.observe(viewLifecycleOwner, Observer { movies ->
            movies?.let { adapter.setMovies(it) }
        })

    }

}
