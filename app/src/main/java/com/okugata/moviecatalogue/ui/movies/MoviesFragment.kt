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
    private lateinit var popularMovieViewModel: PopularMovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        popularMovieViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[PopularMovieViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val movieAdapter = MovieAdapter()
            with(binding.rvMovies) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }

            popularMovieViewModel.popularMovies.observe(viewLifecycleOwner) {
                movieAdapter.setMovies(it)
            }
            popularMovieViewModel.isLoading.observe(viewLifecycleOwner) {
                binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
            }

            popularMovieViewModel.getPopularMovies()
        }
    }
}