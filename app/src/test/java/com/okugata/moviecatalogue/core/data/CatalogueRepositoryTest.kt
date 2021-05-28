package com.okugata.moviecatalogue.core.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.verify
import com.okugata.moviecatalogue.core.data.source.local.LocalDataSource
import com.okugata.moviecatalogue.core.data.source.local.entity.PopularMovieEntity
import com.okugata.moviecatalogue.core.data.source.local.entity.PopularTvShowEntity
import com.okugata.moviecatalogue.core.data.source.remote.RemoteDataSource
import com.okugata.moviecatalogue.core.utils.AppExecutors
import com.okugata.moviecatalogue.core.utils.EntityMock
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
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
    fun getPopularMovies() = runBlocking {
        val dummy: List<PopularMovieEntity> = ArrayList<PopularMovieEntity>().apply {
            add(EntityMock.popularMovie())
        }
        val dummyMovies = flow {
            emit(dummy)
        }
        `when`(local.getPopularMovies()).thenReturn(dummyMovies)

        val movies = repository.getPopularMovies().first()
        verify(local).getPopularMovies()
        assertNotNull(movies.data)
        assertEquals(movies.data?.size, dummy.size)
    }

    @Test
    fun getMovieDetail() = runBlocking {
        val dummy = EntityMock.movieDetail()
        val dummyMovie = flow {
            emit(dummy)
        }
        `when`(local.getMovieDetail(id)).thenReturn(dummyMovie)

        val movie = repository.getMovieDetail(id).first()
        verify(local).getMovieDetail(id)
        assertNotNull(movie.data)
        assertEquals(movie.data?.id, dummy.id)
    }

    @Test
    fun getPopularTvShows() = runBlocking {
        val dummy: List<PopularTvShowEntity> = ArrayList<PopularTvShowEntity>().apply {
            add(EntityMock.popularTvShow())
        }
        val dummyTvShows = flow {
            emit(dummy)
        }
        `when`(local.getPopularTvShows()).thenReturn(dummyTvShows)

        val tvShows = repository.getPopularTvShows().first()
        verify(local).getPopularTvShows()
        assertNotNull(tvShows.data)
        assertEquals(tvShows.data?.size, dummy.size)
    }

    @Test
    fun getTvShowDetail() = runBlocking {
        val dummy = EntityMock.tvShowDetail()
        val dummyTvShow = flow {
            emit(dummy)
        }
        `when`(local.getTvShowDetail(id)).thenReturn(dummyTvShow)

        val tvShow = repository.getTvShowDetail(id).first()
        verify(local).getTvShowDetail(id)
        assertNotNull(tvShow.data)
        assertEquals(tvShow.data?.id, dummy.id)
    }

    @Test
    fun getFavoriteMovies() {
        repository.getFavoriteMovies()
        verify(local).getFavoriteMovies()
    }

    @Test
    fun getFavoriteTvShows() {
        repository.getFavoriteTvShows()
        verify(local).getFavoriteTvShows()
    }
}