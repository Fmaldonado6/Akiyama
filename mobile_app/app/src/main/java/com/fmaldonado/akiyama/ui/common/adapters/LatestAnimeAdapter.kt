package com.fmaldonado.akiyama.ui.common.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fmaldonado.akiyama.data.models.content.Anime
import com.fmaldonado.akiyama.data.models.content.Episode
import com.fmaldonado.akiyama.databinding.LatestAnimeItemBinding

class LatestAnimeAdapter(
    private val animes: List<Anime>? = null,
    private val episodes: List<Episode>? = null
) : RecyclerView.Adapter<LatestAnimeAdapter.LatestAnimeViewHolder>() {

    private var isEpisodeList = false

    inner class LatestAnimeViewHolder(
        val binding: LatestAnimeItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    init {
        isEpisodeList = episodes != null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestAnimeViewHolder {
        val binding = LatestAnimeItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return LatestAnimeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LatestAnimeViewHolder, position: Int) {
        val title = if (isEpisodeList) episodes!![position].title else animes!![position].title
        holder.binding.title.text = title
    }

    override fun getItemCount(): Int = animes?.size ?: episodes?.size ?: 0


}