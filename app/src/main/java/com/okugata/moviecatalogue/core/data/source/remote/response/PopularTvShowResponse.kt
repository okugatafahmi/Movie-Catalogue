package com.okugata.moviecatalogue.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class PopularTvShowResponse(

	@field:SerializedName("results")
	val results: List<PopularTvShow>,
)

data class PopularTvShow(

	@field:SerializedName("first_air_date")
	val firstAirDate: String,

	@field:SerializedName("poster_path")
	val posterPath: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int
)
