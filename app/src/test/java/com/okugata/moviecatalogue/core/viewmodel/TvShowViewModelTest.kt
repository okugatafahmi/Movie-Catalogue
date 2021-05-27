package com.okugata.moviecatalogue.core.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.okugata.moviecatalogue.core.domain.model.TvShow
import com.okugata.moviecatalogue.core.domain.usecase.CatalogueUseCase
import com.okugata.moviecatalogue.core.vo.Resource
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueUseCase: CatalogueUseCase

    @Mock
    private lateinit var observerPopular: Observer<Resource<List<TvShow>>>
    @Mock
    private lateinit var observerFavorite: Observer<List<TvShow>>

    private lateinit var tvShowViewModel: TvShowViewModel
    @Mock
    private lateinit var tvShows: List<TvShow>

    @Before
    fun setUp() {
        tvShowViewModel = TvShowViewModel(catalogueUseCase)
    }

    @Test
    fun getPopularTvShow() {
        val dummyPopularTvShow: Resource<List<TvShow>> = Resource.success(ArrayList())
        val popularTvShow = MutableLiveData<Resource<List<TvShow>>>()
        popularTvShow.value = dummyPopularTvShow

        Mockito.`when`(catalogueUseCase.getPopularTvShows()).thenReturn(popularTvShow)
        val movies = tvShowViewModel.getPopularTvShow().value
        verify(catalogueUseCase).getPopularTvShows()
        assertNotNull(movies)

        tvShowViewModel.getPopularTvShow().observeForever(observerPopular)
        verify(observerPopular).onChanged(dummyPopularTvShow)
    }

    @Test
    fun getFavoriteTvShow() {
        val favoriteTvShow = MutableLiveData<List<TvShow>>()
        favoriteTvShow.value = tvShows

        Mockito.`when`(catalogueUseCase.getFavoriteTvShows()).thenReturn(favoriteTvShow)
        val movies = tvShowViewModel.getFavoriteTvShow().value
        verify(catalogueUseCase).getFavoriteTvShows()
        assertNotNull(movies)

        tvShowViewModel.getFavoriteTvShow().observeForever(observerFavorite)
        verify(observerFavorite).onChanged(tvShows)
    }
}