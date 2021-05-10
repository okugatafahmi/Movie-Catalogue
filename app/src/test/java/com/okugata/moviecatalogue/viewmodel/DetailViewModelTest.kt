package com.okugata.moviecatalogue.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.okugata.moviecatalogue.data.CatalogueRepository
import com.okugata.moviecatalogue.data.source.remote.response.MovieDetailResponse
import com.okugata.moviecatalogue.data.source.remote.response.TvShowDetailResponse
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
    private lateinit var observerMovie: Observer<MovieDetailResponse>
    @Mock
    private lateinit var observerTvShow: Observer<TvShowDetailResponse>

    @Mock
    private lateinit var dummyMovieDetail: MovieDetailResponse
    @Mock
    private lateinit var dummyTvShowDetail: TvShowDetailResponse

    private lateinit var detailViewModel: DetailViewModel
    private var id = 1

    @Before
    fun setUp() {
        detailViewModel = DetailViewModel(catalogueRepository)
    }

    @Test
    fun getMovieDetail() {
        val movieDetail = MutableLiveData<MovieDetailResponse>()
        movieDetail.value = dummyMovieDetail

        `when`(catalogueRepository.getMovieDetail(id)).thenReturn(movieDetail)
        val movie = detailViewModel.getMovieDetail(id).value
        verify(catalogueRepository).getMovieDetail(id)
        assertNotNull(movie)

        detailViewModel.getMovieDetail(id).observeForever(observerMovie)
        verify(observerMovie).onChanged(dummyMovieDetail)
    }

    @Test
    fun getTvShowDetail() {
        val tvShowDetail = MutableLiveData<TvShowDetailResponse>()
        tvShowDetail.value = dummyTvShowDetail

        `when`(catalogueRepository.getTvShowDetail(id)).thenReturn(tvShowDetail)
        val tvShow = detailViewModel.getTvShowDetail(id).value
        verify(catalogueRepository).getTvShowDetail(id)
        assertNotNull(tvShow)

        detailViewModel.getTvShowDetail(id).observeForever(observerTvShow)
        verify(observerTvShow).onChanged(dummyTvShowDetail)
    }
}