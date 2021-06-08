@file:Suppress("unused")

package com.okugata.moviecatalogue

import android.app.Application
import com.okugata.moviecatalogue.core.di.databaseModule
import com.okugata.moviecatalogue.core.di.networkModule
import com.okugata.moviecatalogue.core.di.repositoryModule
import com.okugata.moviecatalogue.di.useCaseModule
import com.okugata.moviecatalogue.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}