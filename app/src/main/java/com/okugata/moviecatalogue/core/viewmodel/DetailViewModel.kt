package com.okugata.moviecatalogue.core.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.okugata.moviecatalogue.core.data.CatalogueRepository
import com.okugata.moviecatalogue.core.domain.model.Movie
import com.okugata.moviecatalogue.core.domain.model.TvShow
import com.okugata.moviecatalogue.core.vo.Resource

class DetailViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {

    fun getMovieDetail(id: Int): LiveData<Resource<Movie>> =
        catalogueRepository.getMovieDetail(id)

    fun getTvShowDetail(id: Int): LiveData<Resource<TvShow>> =
        catalogueRepository.getTvShowDetail(id)

    fun setMovieFavorite(movie: Movie) =
        catalogueRepository.setMovieFavorite(movie, !movie.favorite)

    fun setTvShowFavorite(tvShow: TvShow) =
        catalogueRepository.setTvShowFavorite(tvShow, !tvShow.favorite)
}