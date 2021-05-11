package com.okugata.moviecatalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.okugata.moviecatalogue.data.CatalogueRepository
import com.okugata.moviecatalogue.data.source.local.entity.PopularTvShowEntity
import com.okugata.moviecatalogue.data.source.remote.response.PopularTvShow
import com.okugata.moviecatalogue.vo.Resource

class PopularTvShowViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {

    fun getPopularTvShow(): LiveData<Resource<List<PopularTvShowEntity>>> =
        catalogueRepository.getPopularTvShows()
}