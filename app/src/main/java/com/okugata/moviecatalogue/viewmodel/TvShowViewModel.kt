package com.okugata.moviecatalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.okugata.moviecatalogue.data.CatalogueRepository
import com.okugata.moviecatalogue.data.source.local.entity.PopularTvShowEntity
import com.okugata.moviecatalogue.data.source.local.entity.TvShowDetailEntity
import com.okugata.moviecatalogue.vo.Resource

class TvShowViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {

    fun getPopularTvShow(): LiveData<Resource<List<PopularTvShowEntity>>> =
        catalogueRepository.getPopularTvShows()

    fun getFavoriteTvShow(): LiveData<PagedList<TvShowDetailEntity>> =
        catalogueRepository.getFavoriteTvShows()
}