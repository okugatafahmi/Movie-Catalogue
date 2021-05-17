package com.okugata.moviecatalogue.ui.movies

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.okugata.moviecatalogue.api.ApiConfig.IMAGE_BASE_URL
import com.okugata.moviecatalogue.data.source.local.entity.MovieDetailEntity
import com.okugata.moviecatalogue.databinding.ListItemsBinding
import com.okugata.moviecatalogue.ui.detail.DetailActivity
import com.okugata.moviecatalogue.utils.DeviceLocale

class FavoriteMovieAdapter :
    PagedListAdapter<MovieDetailEntity, FavoriteMovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieDetailEntity>() {
            override fun areItemsTheSame(oldItem: MovieDetailEntity, newItem: MovieDetailEntity) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: MovieDetailEntity,
                newItem: MovieDetailEntity
            ) =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val listItemsBinding =
            ListItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(listItemsBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class MovieViewHolder(private val binding: ListItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieDetailEntity) {
            with(binding) {
                tvItemTitle.text = movie.title
                tvItemDate.text = DeviceLocale.convertDate(movie.releaseDate)
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.EXTRA_ID, movie.id)
                        putExtra(DetailActivity.EXTRA_TITLE, movie.title)
                        putExtra(DetailActivity.EXTRA_IS_MOVIE, true)
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