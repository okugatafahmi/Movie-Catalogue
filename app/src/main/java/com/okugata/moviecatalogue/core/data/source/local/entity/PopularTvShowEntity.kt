package com.okugata.moviecatalogue.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = PopularTvShowEntity.TABLE_NAME)
data class PopularTvShowEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "poster_path")
    val posterPath: String,

    @ColumnInfo(name = "first_air_date")
    val firstAirDate: String
) {
    companion object {
        const val TABLE_NAME = "popular_tv_show"
    }
}