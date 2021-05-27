package com.okugata.moviecatalogue.core.di

import android.content.Context
import com.okugata.moviecatalogue.core.data.CatalogueRepository
import com.okugata.moviecatalogue.core.data.source.local.LocalDataSource
import com.okugata.moviecatalogue.core.data.source.local.room.CatalogueDatabase
import com.okugata.moviecatalogue.core.data.source.remote.RemoteDataSource
import com.okugata.moviecatalogue.core.domain.usecase.CatalogueInteractor
import com.okugata.moviecatalogue.core.domain.usecase.CatalogueUseCase
import com.okugata.moviecatalogue.core.utils.AppExecutors

object Injection {
    private fun provideRepository(context: Context): CatalogueRepository {
        val database = CatalogueDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.catalogueDao())
        val appExecutors = AppExecutors()

        return CatalogueRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideCatalogueUseCase(context: Context): CatalogueUseCase {
        val repository = provideRepository(context)
        return CatalogueInteractor(repository)
    }
}