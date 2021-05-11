package com.okugata.moviecatalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.okugata.moviecatalogue.data.CatalogueRepository
import com.okugata.moviecatalogue.data.source.local.entity.MovieDetailEntity
import com.okugata.moviecatalogue.data.source.local.entity.TvShowDetailEntity
import com.okugata.moviecatalogue.vo.Resource

class DetailViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {

    fun getMovieDetail(id: Int): LiveData<Resource<MovieDetailEntity>> =
        catalogueRepository.getMovieDetail(id)

    fun getTvShowDetail(id: Int): LiveData<Resource<TvShowDetailEntity>> =
        catalogueRepository.getTvShowDetail(id)

    fun setMovieFavorite(movie: MovieDetailEntity) =
        catalogueRepository.setMovieFavorite(movie, !movie.favorite)

    fun setTvShowFavorite(tvShow: TvShowDetailEntity) =
        catalogueRepository.setTvShowFavorite(tvShow, !tvShow.favorite)
}