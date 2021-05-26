package com.okugata.moviecatalogue.core.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.okugata.moviecatalogue.core.data.CatalogueRepository
import com.okugata.moviecatalogue.core.domain.model.TvShow
import com.okugata.moviecatalogue.core.vo.Resource

class TvShowViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {

    fun getPopularTvShow(): LiveData<Resource<List<TvShow>>> =
        catalogueRepository.getPopularTvShows()

    fun getFavoriteTvShow(): LiveData<List<TvShow>> =
        catalogueRepository.getFavoriteTvShows()
}