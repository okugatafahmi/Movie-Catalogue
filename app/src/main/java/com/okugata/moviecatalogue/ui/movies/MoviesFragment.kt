package com.okugata.moviecatalogue.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.okugata.moviecatalogue.databinding.FragmentMoviesBinding
import com.okugata.moviecatalogue.viewmodel.MovieViewModel
import com.okugata.moviecatalogue.viewmodel.ViewModelFactory
import com.okugata.moviecatalogue.vo.Status

class MoviesFragment(
    private val isFavorite: Boolean
) : Fragment() {
    private lateinit var binding: FragmentMoviesBinding
    private lateinit var movieViewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        movieViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(requireActivity())
        )[MovieViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            if (isFavorite) {
                val movieAdapter = FavoriteMovieAdapter()
                with(binding.rvMovies) {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = movieAdapter
                }
                movieViewModel.getFavoriteMovie().observe(viewLifecycleOwner) { movies ->
                    if (movies != null) {
                        movieAdapter.submitData(viewLifecycleOwner.lifecycle, movies)
                    }
                }
            } else {
                val movieAdapter = MovieAdapter()
                with(binding.rvMovies) {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = movieAdapter
                }
                movieViewModel.getPopularMovie().observe(viewLifecycleOwner) { movies ->
                    if (movies != null) {
                        when (movies.status) {
                            Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                            Status.SUCCESS -> {
                                binding.progressBar.visibility = View.GONE
                                movies.data?.let { movieAdapter.setMovies(it) }
                            }
                            Status.ERROR -> {
                                binding.progressBar.visibility = View.GONE
                                Toast.makeText(
                                    requireContext(),
                                    "There is error",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
            }
        }
    }
}