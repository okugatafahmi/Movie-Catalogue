package com.okugata.moviecatalogue.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.okugata.moviecatalogue.core.data.Resource
import com.okugata.moviecatalogue.core.ui.tvshow.TvShowAdapter
import com.okugata.moviecatalogue.databinding.FragmentTvShowsBinding
import com.okugata.moviecatalogue.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowsFragment(
    private val isFavorite: Boolean
) : Fragment() {
    private var _binding: FragmentTvShowsBinding? = null
    private val binding
        get() = _binding!!
    private val tvShowViewModel: TvShowViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvShowsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val tvShowAdapter = TvShowAdapter().apply {
                onItemClick = {
                    val intent = Intent(activity, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.EXTRA_ID, it.id)
                        putExtra(DetailActivity.EXTRA_TITLE, it.name)
                        putExtra(DetailActivity.EXTRA_IS_MOVIE, false)
                    }
                    startActivity(intent)
                }
            }
            with(binding.rvTvShows) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
            if (isFavorite) {
                tvShowViewModel.getFavoriteTvShow().observe(viewLifecycleOwner) { tvShows ->
                    if (tvShows != null) {
                        tvShowAdapter.setTvShows(tvShows)
                    }
                }
            } else {
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