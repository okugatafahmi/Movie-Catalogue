package com.okugata.moviecatalogue.ui.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.okugata.moviecatalogue.data.source.local.entity.PopularTvShowEntity
import com.okugata.moviecatalogue.databinding.FragmentTvShowsBinding
import com.okugata.moviecatalogue.viewmodel.TvShowViewModel
import com.okugata.moviecatalogue.viewmodel.ViewModelFactory
import com.okugata.moviecatalogue.vo.Status
import java.util.*

class TvShowsFragment(
    private val isFavorite: Boolean
) : Fragment() {
    private lateinit var binding: FragmentTvShowsBinding
    private lateinit var tvShowViewModel: TvShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvShowsBinding.inflate(layoutInflater, container, false)
        tvShowViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(requireActivity())
        )[TvShowViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val tvShowAdapter = TvShowAdapter()
            with(binding.rvTvShows) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }

            if (isFavorite) {
                tvShowViewModel.getFavoriteTvShow().observe(viewLifecycleOwner) { movies ->
                    if (movies != null) {
                        val list = LinkedList<PopularTvShowEntity>()
                        for (item in movies) {
                            val movie = PopularTvShowEntity(
                                item.id,
                                item.name,
                                item.posterPath,
                                item.firstAirDate
                            )
                            list.add(movie)
                        }
                        tvShowAdapter.setTvShows(list)
                    }
                }
            } else {
                tvShowViewModel.getPopularTvShow().observe(viewLifecycleOwner) { movies ->
                    if (movies != null) {
                        when (movies.status) {
                            Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                            Status.SUCCESS -> {
                                binding.progressBar.visibility = View.GONE
                                movies.data?.let { tvShowAdapter.setTvShows(it) }
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