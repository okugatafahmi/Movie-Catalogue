package com.okugata.moviecatalogue.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = MovieDetailEntity.TABLE_NAME)
data class MovieDetailEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "genres")
    val genres: String,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "runtime")
    val runtime: Int,

    @ColumnInfo(name = "poster_path")
    val posterPath: String,

    @ColumnInfo(name = "release_date")
    val releaseDate: String,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false
) {
    companion object {
        const val TABLE_NAME = "movie_detail"
    }
}