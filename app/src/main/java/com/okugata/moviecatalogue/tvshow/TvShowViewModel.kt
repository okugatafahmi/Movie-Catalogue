package com.okugata.moviecatalogue.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.okugata.moviecatalogue.core.data.Resource
import com.okugata.moviecatalogue.core.domain.model.TvShow
import com.okugata.moviecatalogue.core.domain.usecase.CatalogueUseCase

class TvShowViewModel(private val catalogueUseCase: CatalogueUseCase) : ViewModel() {

    fun getPopularTvShow(): LiveData<Resource<List<TvShow>>> =
        catalogueUseCase.getPopularTvShows().asLiveData()

    fun getFavoriteTvShow(): LiveData<List<TvShow>> =
        catalogueUseCase.getFavoriteTvShows().asLiveData()
}