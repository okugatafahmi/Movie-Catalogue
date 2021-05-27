package com.okugata.moviecatalogue.core.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.okugata.moviecatalogue.core.domain.model.TvShow
import com.okugata.moviecatalogue.core.domain.usecase.CatalogueUseCase
import com.okugata.moviecatalogue.core.vo.Resource

class TvShowViewModel(private val catalogueUseCase: CatalogueUseCase) : ViewModel() {

    fun getPopularTvShow(): LiveData<Resource<List<TvShow>>> =
        catalogueUseCase.getPopularTvShows()

    fun getFavoriteTvShow(): LiveData<List<TvShow>> =
        catalogueUseCase.getFavoriteTvShows()
}