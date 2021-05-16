package com.okugata.moviecatalogue.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.nhaarman.mockitokotlin2.verify
import com.okugata.moviecatalogue.data.source.local.LocalDataSource
import com.okugata.moviecatalogue.data.source.local.entity.MovieDetailEntity
import com.okugata.moviecatalogue.data.source.local.entity.PopularMovieEntity
import com.okugata.moviecatalogue.data.source.local.entity.PopularTvShowEntity
import com.okugata.moviecatalogue.data.source.local.entity.TvShowDetailEntity
import com.okugata.moviecatalogue.data.source.remote.RemoteDataSource
import com.okugata.moviecatalogue.utils.AppExecutors
import com.okugata.moviecatalogue.utils.LiveDataTestUtil
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

    @Before
    fun setUp() {
        repository = FakeCatalogueRepository(remote, local, appExecutors)
    }

    @Test
    fun getPopularMovies() {
        val dummy: List<PopularMovieEntity> = ArrayList<PopularMovieEntity>().apply{
            add(mock(PopularMovieEntity::class.java))
        }
        val dummyMovies = MutableLiveData<List<PopularMovieEntity>>()
        dummyMovies.value = dummy
        `when`(local.getPopularMovies()).thenReturn(dummyMovies)

        val movies = LiveDataTestUtil.getValue(repository.getPopularMovies())
        verify(local).getPopularMovies()
        assertNotNull(movies.data)
        assertEquals(movies.data, dummy)
    }

    @Test
    fun getMovieDetail() {
        val dummy = mock(MovieDetailEntity::class.java)
        val dummyMovie = MutableLiveData<MovieDetailEntity>()
        dummyMovie.value = dummy
        `when`(local.getMovieDetail(id)).thenReturn(dummyMovie)

        val movie = LiveDataTestUtil.getValue(repository.getMovieDetail(id))
        verify(local).getMovieDetail(id)
        assertNotNull(movie.data)
        assertEquals(movie.data, dummy)
    }

    @Test
    fun getPopularTvShows() {
        val dummy: List<PopularTvShowEntity> = ArrayList<PopularTvShowEntity>().apply{
            add(mock(PopularTvShowEntity::class.java))
        }
        val dummyTvShows = MutableLiveData<List<PopularTvShowEntity>>()
        dummyTvShows.value = dummy
        `when`(local.getPopularTvShows()).thenReturn(dummyTvShows)

        val tvShows = LiveDataTestUtil.getValue(repository.getPopularTvShows())
        verify(local).getPopularTvShows()
        assertNotNull(tvShows.data)
        assertEquals(tvShows.data, dummy)
    }

    @Test
    fun getTvShowDetail() {
        val dummy = mock(TvShowDetailEntity::class.java)
        val dummyTvShow = MutableLiveData<TvShowDetailEntity>()
        dummyTvShow.value = dummy
        `when`(local.getTvShowDetail(id)).thenReturn(dummyTvShow)

        val tvShow = LiveDataTestUtil.getValue(repository.getTvShowDetail(id))
        verify(local).getTvShowDetail(id)
        assertNotNull(tvShow.data)
        assertEquals(tvShow.data, dummy)
    }

    @Test
    fun getFavoriteMovies() {
        val dummy: List<MovieDetailEntity> = ArrayList<MovieDetailEntity>().apply{
            add(mock(MovieDetailEntity::class.java))
        }
        val dummyMovies = MutableLiveData<List<MovieDetailEntity>>()
        dummyMovies.value = dummy
        `when`(local.getFavoriteMovies()).thenReturn(dummyMovies)

        val movies = LiveDataTestUtil.getValue(repository.getFavoriteMovies())
        verify(local).getFavoriteMovies()
        assertNotNull(movies)
        assertEquals(movies, dummy)
    }

    @Test
    fun getFavoriteTvShows() {
        val dummy: List<TvShowDetailEntity> = ArrayList<TvShowDetailEntity>().apply{
            add(mock(TvShowDetailEntity::class.java))
        }
        val dummyTvShows = MutableLiveData<List<TvShowDetailEntity>>()
        dummyTvShows.value = dummy
        `when`(local.getFavoriteTvShows()).thenReturn(dummyTvShows)

        val tvShows = LiveDataTestUtil.getValue(repository.getFavoriteTvShows())
        verify(local).getFavoriteTvShows()
        assertNotNull(tvShows)
        assertEquals(tvShows, dummy)
    }
}