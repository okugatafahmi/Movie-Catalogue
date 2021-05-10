package com.okugata.moviecatalogue.data.source.remote.response

import com.google.gson.annotations.SerializedName
import java.lang.StringBuilder

data class GenresItem(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int
) {
    companion object {
        fun concatGenre(genres: List<GenresItem>): String {
            val res = StringBuilder()
            for (i in genres.indices){
                res.append(genres[i].name)
                if (i<genres.size-1) {
                    res.append(", ")
                }
            }
            return res.toString()
        }
    }
}