package com.okugata.moviecatalogue.core.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nhaarman.mockitokotlin2.verify
import com.okugata.moviecatalogue.core.data.source.local.LocalDataSource
import com.okugata.moviecatalogue.core.data.source.local.entity.MovieDetailEntity
import com.okugata.moviecatalogue.core.data.source.local.entity.PopularMovieEntity
import com.okugata.moviecatalogue.core.data.source.local.entity.PopularTvShowEntity
import com.okugata.moviecatalogue.core.data.source.local.entity.TvShowDetailEntity
import com.okugata.moviecatalogue.core.data.source.remote.RemoteDataSource
import com.okugata.moviecatalogue.core.utils.AppExecutors
import com.okugata.moviecatalogue.core.utils.EntityMock
import com.okugata.moviecatalogue.core.utils.LiveDataTestUtil
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CatalogueRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var remote: RemoteDataSource
    @Mock
    private lateinit var local: LocalDataSource
    @Mock
    private lateinit var appExecutors: AppExecutors
    private lateinit var repository: FakeCatalogueRepository
    private val id = 1

    @Mock
    private lateinit var dataSourceFactoryMovie: LiveData<List<MovieDetailEntity>>
    @Mock
    private lateinit var dataSourceFactoryTvShow: LiveData<List<TvShowDetailEntity>>

    @Before
    fun setUp() {
        repository = FakeCatalogueRepository(remote, local, appExecutors)
    }

    @Test
    fun getPopularMovies() {
        val dummy: List<PopularMovieEntity> = ArrayList<PopularMovieEntity>().apply{
            add(EntityMock.popularMovie())
        }
        val dummyMovies = MutableLiveData<List<PopularMovieEntity>>()
        dummyMovies.value = dummy
        `when`(local.getPopularMovies()).thenReturn(dummyMovies)

        val movies = LiveDataTestUtil.getValue(repository.getPopularMovies())
        verify(local).getPopularMovies()
        assertNotNull(movies.data)
        assertEquals(movies.data?.size, dummy.size)
    }

    @Test
    fun getMovieDetail() {
        val dummy = EntityMock.movieDetail()
        val dummyMovie = MutableLiveData<MovieDetailEntity>()
        dummyMovie.value = dummy
        `when`(local.getMovieDetail(id)).thenReturn(dummyMovie)

        val movie = LiveDataTestUtil.getValue(repository.getMovieDetail(id))
        verify(local).getMovieDetail(id)
        assertNotNull(movie.data)
        assertEquals(movie.data?.id, dummy.id)
    }

    @Test
    fun getPopularTvShows() {
        val dummy: List<PopularTvShowEntity> = ArrayList<PopularTvShowEntity>().apply{
            add(EntityMock.popularTvShow())
        }
        val dummyTvShows = MutableLiveData<List<PopularTvShowEntity>>()
        dummyTvShows.value = dummy
        `when`(local.getPopularTvShows()).thenReturn(dummyTvShows)

        val tvShows = LiveDataTestUtil.getValue(repository.getPopularTvShows())
        verify(local).getPopularTvShows()
        assertNotNull(tvShows.data)
        assertEquals(tvShows.data?.size, dummy.size)
    }

    @Test
    fun getTvShowDetail() {
        val dummy = EntityMock.tvShowDetail()
        val dummyTvShow = MutableLiveData<TvShowDetailEntity>()
        dummyTvShow.value = dummy
        `when`(local.getTvShowDetail(id)).thenReturn(dummyTvShow)

        val tvShow = LiveDataTestUtil.getValue(repository.getTvShowDetail(id))
        verify(local).getTvShowDetail(id)
        assertNotNull(tvShow.data)
        assertEquals(tvShow.data?.id, dummy.id)
    }

    @Test
    fun getFavoriteMovies() {
        `when`(local.getFavoriteMovies()).thenReturn(dataSourceFactoryMovie)
        repository.getFavoriteMovies()
        verify(local).getFavoriteMovies()
    }

    @Test
    fun getFavoriteTvShows() {
        `when`(local.getFavoriteTvShows()).thenReturn(dataSourceFactoryTvShow)
        repository.getFavoriteTvShows()
        verify(local).getFavoriteTvShows()
    }
}