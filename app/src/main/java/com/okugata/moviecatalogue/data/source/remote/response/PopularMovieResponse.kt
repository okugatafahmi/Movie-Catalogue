package com.okugata.moviecatalogue.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class PopularMovieResponse(

	@field:SerializedName("page")
	val page: Int,

	@field:SerializedName("total_pages")
	val totalPages: Int,

	@field:SerializedName("results")
	val results: List<PopularMovie>,

	@field:SerializedName("total_results")
	val totalResults: Int
)

data class PopularMovie(

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("poster_path")
	val posterPath: String,

	@field:SerializedName("release_date")
	val releaseDate: String,

	@field:SerializedName("id")
	val id: Int
)
