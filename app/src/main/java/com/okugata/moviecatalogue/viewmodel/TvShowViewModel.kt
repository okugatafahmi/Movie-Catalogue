package com.okugata.moviecatalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.okugata.moviecatalogue.data.CatalogueRepository
import com.okugata.moviecatalogue.data.source.local.entity.PopularTvShowEntity
import com.okugata.moviecatalogue.data.source.local.entity.TvShowDetailEntity
import com.okugata.moviecatalogue.vo.Resource

class TvShowViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {

    fun getPopularTvShow(): LiveData<Resource<List<PopularTvShowEntity>>> =
        catalogueRepository.getPopularTvShows()

    fun getFavoriteTvShow(): LiveData<List<TvShowDetailEntity>> =
        catalogueRepository.getFavoriteTvShows()
}