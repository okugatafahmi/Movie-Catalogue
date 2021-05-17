package com.okugata.moviecatalogue.ui.tvshows

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
import com.okugata.moviecatalogue.data.source.local.entity.TvShowDetailEntity
import com.okugata.moviecatalogue.databinding.ListItemsBinding
import com.okugata.moviecatalogue.ui.detail.DetailActivity
import com.okugata.moviecatalogue.utils.DeviceLocale

class FavoriteTvShowAdapter :
    PagedListAdapter<TvShowDetailEntity, FavoriteTvShowAdapter.TvShowViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowDetailEntity>() {
            override fun areItemsTheSame(oldItem: TvShowDetailEntity, newItem: TvShowDetailEntity) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: TvShowDetailEntity,
                newItem: TvShowDetailEntity
            ) =
                oldItem == newItem
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val listItemsBinding =
            ListItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(listItemsBinding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }


    class TvShowViewHolder(private val binding: ListItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShowDetailEntity) {
            with(binding) {
                tvItemTitle.text = tvShow.name
                tvItemDate.text = DeviceLocale.convertDate(tvShow.firstAirDate)
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.EXTRA_ID, tvShow.id)
                        putExtra(DetailActivity.EXTRA_TITLE, tvShow.name)
                        putExtra(DetailActivity.EXTRA_IS_MOVIE, false)
                    }
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load("$IMAGE_BASE_URL${tvShow.posterPath}")
                    .placeholder(ColorDrawable(Color.GRAY))
                    .into(imgPoster)
            }
        }
    }
}