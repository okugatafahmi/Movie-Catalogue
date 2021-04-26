package com.okugata.moviecatalogue.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.okugata.moviecatalogue.databinding.FragmentMoviesBinding

class MoviesFragment : Fragment() {
    private lateinit var binding: FragmentMoviesBinding
    private lateinit var movieViewModel: MovieViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        movieViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MovieViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val movies = movieViewModel.getMovies()
            val movieAdapter = MovieAdapter()
            movieAdapter.setMovies(movies)
            with(binding.rvMovies) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }
}