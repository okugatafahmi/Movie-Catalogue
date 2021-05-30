package com.okugata.moviecatalogue.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = TvShowDetailEntity.TABLE_NAME)
data class TvShowDetailEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "number_of_episodes")
    val numberOfEpisodes: Int,

    @ColumnInfo(name = "genres")
    val genres: String,

    @ColumnInfo(name = "first_air_date")
    val firstAirDate: String,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "poster_path")
    val posterPath: String,

    @ColumnInfo(name = "status")
    val status: String,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false
) {
    companion object {
        const val TABLE_NAME = "tv_show_detail"
    }
}