package com.okugata.moviecatalogue.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.okugata.moviecatalogue.data.CatalogueRepository
import com.okugata.moviecatalogue.data.source.remote.response.MovieDetailResponse
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
class MovieDetailViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<MovieDetailResponse>

    @Mock
    private lateinit var dummyMovieDetail: MovieDetailResponse

    private lateinit var movieDetailViewModel: MovieDetailViewModel
    private var id = 1

    @Before
    fun setUp() {
        movieDetailViewModel = MovieDetailViewModel(catalogueRepository)
    }

    @Test
    fun getMovieDetail() {
        val movieDetail = MutableLiveData<MovieDetailResponse>()
        movieDetail.value = dummyMovieDetail

        `when`(catalogueRepository.getMovieDetail(id)).thenReturn(movieDetail)
        val movie = movieDetailViewModel.getMovieDetail(id).value
        verify(catalogueRepository).getMovieDetail(id)
        assertNotNull(movie)

        movieDetailViewModel.getMovieDetail(id).observeForever(observer)
        verify(observer).onChanged(dummyMovieDetail)
    }
}