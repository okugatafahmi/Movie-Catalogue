package com.okugata.moviecatalogue.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GenresItem(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int
) {
    companion object {
        fun concatGenre(genres: List<GenresItem>): String {
            return genres.joinToString { it.name }
        }
    }
}