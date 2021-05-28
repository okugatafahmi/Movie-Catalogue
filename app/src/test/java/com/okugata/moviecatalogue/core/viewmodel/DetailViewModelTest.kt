package com.okugata.moviecatalogue.core.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.okugata.moviecatalogue.core.data.Resource
import com.okugata.moviecatalogue.core.domain.model.Movie
import com.okugata.moviecatalogue.core.domain.model.TvShow
import com.okugata.moviecatalogue.core.domain.usecase.CatalogueUseCase
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueUseCase: CatalogueUseCase

    @Mock
    private lateinit var observerMovie: Observer<Resource<Movie>>
    @Mock
    private lateinit var observerTvShow: Observer<Resource<TvShow>>

    @Mock
    private lateinit var dummyMovieDetail: Movie
    @Mock
    private lateinit var dummyTvShowDetail: TvShow

    private lateinit var detailViewModel: DetailViewModel
    private var id = 1

    @Before
    fun setUp() {
        detailViewModel = DetailViewModel(catalogueUseCase)
    }

    @Test
    fun getMovieDetail() {
        val dummy = Resource.Success(dummyMovieDetail)
        val movieDetail = flow {
            emit(dummy)
        }

        `when`(catalogueUseCase.getMovieDetail(id)).thenReturn(movieDetail)
        val movie = detailViewModel.getMovieDetail(id).value
        verify(catalogueUseCase).getMovieDetail(id)
        assertNotNull(movie)

        detailViewModel.getMovieDetail(id).observeForever(observerMovie)
        verify(observerMovie).onChanged(dummy)
    }

    @Test
    fun getTvShowDetail() {
        val dummy = Resource.Success(dummyTvShowDetail)
        val tvShowDetail = flow {
            emit(dummy)
        }

        `when`(catalogueUseCase.getTvShowDetail(id)).thenReturn(tvShowDetail)
        val tvShow = detailViewModel.getTvShowDetail(id).value
        verify(catalogueUseCase).getTvShowDetail(id)
        assertNotNull(tvShow)

        detailViewModel.getTvShowDetail(id).observeForever(observerTvShow)
        verify(observerTvShow).onChanged(dummy)
    }

    @Test
    fun setMovieFavorite() {
        detailViewModel.setMovieFavorite(dummyMovieDetail)
        verify(catalogueUseCase).setMovieFavorite(dummyMovieDetail, !dummyMovieDetail.favorite)
    }

    @Test
    fun setTvShowFavorite() {
        detailViewModel.setTvShowFavorite(dummyTvShowDetail)
        verify(catalogueUseCase).setTvShowFavorite(dummyTvShowDetail, !dummyTvShowDetail.favorite)
    }
}