package com.okugata.moviecatalogue.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class PopularTvShowResponse(

	@field:SerializedName("page")
	val page: Int,

	@field:SerializedName("total_pages")
	val totalPages: Int,

	@field:SerializedName("results")
	val results: List<PopularTvShow>,

	@field:SerializedName("total_results")
	val totalResults: Int
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
