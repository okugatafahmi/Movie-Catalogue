package com.okugata.moviecatalogue.core.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.okugata.moviecatalogue.core.data.CatalogueRepository
import com.okugata.moviecatalogue.core.domain.model.Movie
import com.okugata.moviecatalogue.core.domain.model.TvShow
import com.okugata.moviecatalogue.core.vo.Resource
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
    private lateinit var catalogueRepository: CatalogueRepository

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
        detailViewModel = DetailViewModel(catalogueRepository)
    }

    @Test
    fun getMovieDetail() {
        val dummy = Resource.success(dummyMovieDetail)
        val movieDetail = MutableLiveData<Resource<Movie>>()
        movieDetail.value = dummy

        `when`(catalogueRepository.getMovieDetail(id)).thenReturn(movieDetail)
        val movie = detailViewModel.getMovieDetail(id).value
        verify(catalogueRepository).getMovieDetail(id)
        assertNotNull(movie)

        detailViewModel.getMovieDetail(id).observeForever(observerMovie)
        verify(observerMovie).onChanged(dummy)
    }

    @Test
    fun getTvShowDetail() {
        val dummy = Resource.success(dummyTvShowDetail)
        val tvShowDetail = MutableLiveData<Resource<TvShow>>()
        tvShowDetail.value = dummy

        `when`(catalogueRepository.getTvShowDetail(id)).thenReturn(tvShowDetail)
        val tvShow = detailViewModel.getTvShowDetail(id).value
        verify(catalogueRepository).getTvShowDetail(id)
        assertNotNull(tvShow)

        detailViewModel.getTvShowDetail(id).observeForever(observerTvShow)
        verify(observerTvShow).onChanged(dummy)
    }

    @Test
    fun setMovieFavorite() {
        detailViewModel.setMovieFavorite(dummyMovieDetail)
        verify(catalogueRepository).setMovieFavorite(dummyMovieDetail, !dummyMovieDetail.favorite)
    }

    @Test
    fun setTvShowFavorite() {
        detailViewModel.setTvShowFavorite(dummyTvShowDetail)
        verify(catalogueRepository).setTvShowFavorite(dummyTvShowDetail, !dummyTvShowDetail.favorite)
    }
}