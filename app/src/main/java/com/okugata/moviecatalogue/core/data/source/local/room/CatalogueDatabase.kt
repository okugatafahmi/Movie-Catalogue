package com.okugata.moviecatalogue.core.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.okugata.moviecatalogue.core.data.source.local.entity.MovieDetailEntity
import com.okugata.moviecatalogue.core.data.source.local.entity.PopularMovieEntity
import com.okugata.moviecatalogue.core.data.source.local.entity.PopularTvShowEntity
import com.okugata.moviecatalogue.core.data.source.local.entity.TvShowDetailEntity

@Database(
    entities = [MovieDetailEntity::class, TvShowDetailEntity::class, PopularMovieEntity::class, PopularTvShowEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CatalogueDatabase : RoomDatabase() {
    abstract fun catalogueDao(): CatalogueDao

    companion object {

        @Volatile
        private var INSTANCE: CatalogueDatabase? = null

        fun getInstance(context: Context): CatalogueDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    CatalogueDatabase::class.java,
                    "catalogue_database"
                ).build().apply {
                    INSTANCE = this
                }
            }
    }
}