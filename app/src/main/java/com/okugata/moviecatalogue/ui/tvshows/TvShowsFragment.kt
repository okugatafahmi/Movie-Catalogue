package com.okugata.moviecatalogue.ui.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.okugata.moviecatalogue.databinding.FragmentTvShowsBinding

class TvShowsFragment : Fragment() {
    private lateinit var binding: FragmentTvShowsBinding
    private lateinit var tvShowViewModel: TvShowViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentTvShowsBinding.inflate(layoutInflater, container, false)
        tvShowViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[TvShowViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val tvShows = tvShowViewModel.getTvShows()
            val tvShowAdapter = TvShowAdapter()
            tvShowAdapter.setTvShows(tvShows)
            with(binding.rvTvShows) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }
    }
}