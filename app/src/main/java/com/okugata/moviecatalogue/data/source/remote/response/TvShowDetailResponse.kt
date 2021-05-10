package com.okugata.moviecatalogue.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvShowDetailResponse(

	@field:SerializedName("number_of_episodes")
	val numberOfEpisodes: Int,

	@field:SerializedName("genres")
	val genres: List<GenresItem>,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("first_air_date")
	val firstAirDate: String,

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("poster_path")
	val posterPath: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("status")
	val status: String
) {
	fun getGenres() = GenresItem.concatGenre(genres)
}