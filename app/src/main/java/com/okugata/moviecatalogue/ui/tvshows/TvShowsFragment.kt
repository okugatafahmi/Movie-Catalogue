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
    private lateinit var popularTvShowViewModel: PopularTvShowViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentTvShowsBinding.inflate(layoutInflater, container, false)
        popularTvShowViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[PopularTvShowViewModel::class.java]
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

            popularTvShowViewModel.popularTvShows.observe(viewLifecycleOwner) {
                tvShowAdapter.setTvShows(it)
            }
            popularTvShowViewModel.isLoading.observe(viewLifecycleOwner) {
                binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
            }

            popularTvShowViewModel.getPopularTvShows()
        }
    }
}