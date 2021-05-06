package com.okugata.moviecatalogue.ui.movies

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.okugata.moviecatalogue.api.ApiConfig.IMAGE_BASE_URL
import com.okugata.moviecatalogue.data.source.remote.response.PopularMovie
import com.okugata.moviecatalogue.databinding.ListItemsBinding
import com.okugata.moviecatalogue.utils.DeviceLocale
import kotlin.collections.ArrayList

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var listMovies = ArrayList<PopularMovie>()

    fun setMovies(movies: List<PopularMovie>?) {
        movies?.let {
            listMovies.clear()
            listMovies.addAll(it)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val listItemsBinding =
            ListItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(listItemsBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listMovies[position])
    }

    override fun getItemCount(): Int = listMovies.size


    class MovieViewHolder(private val binding: ListItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: PopularMovie) {
            with(binding) {
                tvItemTitle.text = movie.title
                tvItemDate.text = DeviceLocale.convertDate(movie.releaseDate)
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, MovieDetailActivity::class.java).apply {
                        putExtra(MovieDetailActivity.EXTRA_MOVIE_ID, movie.id)
                        putExtra(MovieDetailActivity.EXTRA_MOVIE_TITLE, movie.title)
                    }
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load("$IMAGE_BASE_URL${movie.posterPath}")
                    .placeholder(ColorDrawable(Color.GRAY))
                    .into(imgPoster)
            }
        }
    }
}