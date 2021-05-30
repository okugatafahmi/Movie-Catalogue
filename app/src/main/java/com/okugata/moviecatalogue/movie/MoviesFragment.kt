package com.okugata.moviecatalogue.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.okugata.moviecatalogue.core.data.Resource
import com.okugata.moviecatalogue.core.ui.movie.MovieAdapter
import com.okugata.moviecatalogue.databinding.FragmentMoviesBinding
import com.okugata.moviecatalogue.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment(
    private val isFavorite: Boolean
) : Fragment() {
    private var _binding: FragmentMoviesBinding? = null
    private val binding
        get() = _binding!!
    private val movieViewModel: MovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val movieAdapter = MovieAdapter().apply {
                onItemClick = {
                    val intent = Intent(activity, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.EXTRA_ID, it.id)
                        putExtra(DetailActivity.EXTRA_TITLE, it.title)
                        putExtra(DetailActivity.EXTRA_IS_MOVIE, true)
                    }
                    startActivity(intent)
                }
            }
            with(binding.rvMovies) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
            if (isFavorite) {
                movieViewModel.getFavoriteMovie().observe(viewLifecycleOwner) { movies ->
                    if (movies != null) {
                        movieAdapter.setMovies(movies)
                    }
                }
            } else {
                movieViewModel.getPopularMovie().observe(viewLifecycleOwner) { movies ->
                    if (movies != null) {
                        when (movies) {
                            is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                            is Resource.Success -> {
                                binding.progressBar.visibility = View.GONE
                                movies.data?.let { movieAdapter.setMovies(it) }
                            }
                            is Resource.Error -> {
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