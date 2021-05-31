package com.okugata.moviecatalogue.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.okugata.moviecatalogue.core.data.Resource
import com.okugata.moviecatalogue.core.domain.model.Movie
import com.okugata.moviecatalogue.core.domain.model.TvShow
import com.okugata.moviecatalogue.core.domain.usecase.CatalogueUseCase

class DetailViewModel(private val catalogueUseCase: CatalogueUseCase) : ViewModel() {

    fun getMovieDetail(id: Int): LiveData<Resource<Movie>> =
        catalogueUseCase.getMovieDetail(id).asLiveData()

    fun getTvShowDetail(id: Int): LiveData<Resource<TvShow>> =
        catalogueUseCase.getTvShowDetail(id).asLiveData()

    fun setMovieFavorite(movie: Movie) =
        catalogueUseCase.setMovieFavorite(movie, !movie.favorite)

    fun setTvShowFavorite(tvShow: TvShow) =
        catalogueUseCase.setTvShowFavorite(tvShow, !tvShow.favorite)
}