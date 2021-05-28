package com.okugata.moviecatalogue.core.ui.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.okugata.moviecatalogue.core.data.Resource
import com.okugata.moviecatalogue.databinding.FragmentTvShowsBinding
import com.okugata.moviecatalogue.core.viewmodel.TvShowViewModel
import com.okugata.moviecatalogue.core.viewmodel.ViewModelFactory

class TvShowsFragment(
    private val isFavorite: Boolean
) : Fragment() {
    private var _binding: FragmentTvShowsBinding? = null
    private val binding
        get() = _binding!!
    private lateinit var tvShowViewModel: TvShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvShowsBinding.inflate(layoutInflater, container, false)
        tvShowViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(requireActivity())
        )[TvShowViewModel::class.java]
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            if (isFavorite) {
                val tvShowAdapter = FavoriteTvShowAdapter()
                with(binding.rvTvShows) {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = tvShowAdapter
                }
                tvShowViewModel.getFavoriteTvShow().observe(viewLifecycleOwner) { tvShows ->
                    if (tvShows != null) {
                        tvShowAdapter.setTvShows(tvShows)
                    }
                }
            } else {
                val tvShowAdapter = TvShowAdapter()
                with(binding.rvTvShows) {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = tvShowAdapter
                }
                tvShowViewModel.getPopularTvShow().observe(viewLifecycleOwner) { tvShows ->
                    if (tvShows != null) {
                        when (tvShows) {
                            is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                            is Resource.Success -> {
                                binding.progressBar.visibility = View.GONE
                                tvShows.data?.let { tvShowAdapter.setTvShows(it) }
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