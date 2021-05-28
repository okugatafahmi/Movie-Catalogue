package com.okugata.moviecatalogue.core.ui.tvshows

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.okugata.moviecatalogue.core.data.source.remote.network.ApiConfig.getImageUrl
import com.okugata.moviecatalogue.core.domain.model.TvShow
import com.okugata.moviecatalogue.databinding.ListItemsBinding
import com.okugata.moviecatalogue.core.ui.detail.DetailActivity
import com.okugata.moviecatalogue.core.utils.DeviceLocale

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {
    private var listTvShows = ArrayList<TvShow>()

    fun setTvShows(tvShows: List<TvShow>) {
        listTvShows.clear()
        listTvShows.addAll(tvShows)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val listItemsBinding =
            ListItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(listItemsBinding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        holder.bind(listTvShows[position])
    }

    override fun getItemCount(): Int = listTvShows.size


    class TvShowViewHolder(private val binding: ListItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShow) {
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
                    .load(getImageUrl(tvShow.posterPath))
                    .placeholder(ColorDrawable(Color.GRAY))
                    .into(imgPoster)
            }
        }
    }
}