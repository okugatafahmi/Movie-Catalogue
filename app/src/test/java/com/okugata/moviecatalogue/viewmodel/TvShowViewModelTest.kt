package com.okugata.moviecatalogue.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.okugata.moviecatalogue.data.CatalogueRepository
import com.okugata.moviecatalogue.data.source.local.entity.PopularTvShowEntity
import com.okugata.moviecatalogue.data.source.local.entity.TvShowDetailEntity
import com.okugata.moviecatalogue.vo.Resource
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
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var observerPopular: Observer<Resource<List<PopularTvShowEntity>>>
    @Mock
    private lateinit var observerFavorite: Observer<List<TvShowDetailEntity>>

    private lateinit var tvShowViewModel: TvShowViewModel
    @Mock
    private lateinit var pagedList: PagedList<TvShowDetailEntity>

    @Before
    fun setUp() {
        tvShowViewModel = TvShowViewModel(catalogueRepository)
    }

    @Test
    fun getPopularTvShow() {
        val dummyPopularTvShow: Resource<List<PopularTvShowEntity>> = Resource.success(ArrayList())
        val popularTvShow = MutableLiveData<Resource<List<PopularTvShowEntity>>>()
        popularTvShow.value = dummyPopularTvShow

        Mockito.`when`(catalogueRepository.getPopularTvShows()).thenReturn(popularTvShow)
        val movies = tvShowViewModel.getPopularTvShow().value
        verify(catalogueRepository).getPopularTvShows()
        assertNotNull(movies)

        tvShowViewModel.getPopularTvShow().observeForever(observerPopular)
        verify(observerPopular).onChanged(dummyPopularTvShow)
    }

    @Test
    fun getFavoriteTvShow() {
        val favoriteTvShow = MutableLiveData<PagedList<TvShowDetailEntity>>()
        favoriteTvShow.value = pagedList

        Mockito.`when`(catalogueRepository.getFavoriteTvShows()).thenReturn(favoriteTvShow)
        val movies = tvShowViewModel.getFavoriteTvShow().value
        verify(catalogueRepository).getFavoriteTvShows()
        assertNotNull(movies)

        tvShowViewModel.getFavoriteTvShow().observeForever(observerFavorite)
        verify(observerFavorite).onChanged(pagedList)
    }
}