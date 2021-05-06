package com.okugata.moviecatalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.okugata.moviecatalogue.data.source.CatalogueRepository
import com.okugata.moviecatalogue.data.source.remote.response.PopularTvShow

class PopularTvShowViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {

    fun getPopularTvShows(): LiveData<List<PopularTvShow>> = catalogueRepository.getPopularTvShow()
}