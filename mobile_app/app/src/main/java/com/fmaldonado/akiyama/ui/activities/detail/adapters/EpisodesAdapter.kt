package com.fmaldonado.akiyama.ui.activities.detail.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.fmaldonado.akiyama.R
import com.fmaldonado.akiyama.data.models.content.Episode
import com.fmaldonado.akiyama.databinding.EpisodeItemBinding

class EpisodesAdapter(
    private val episodes: List<Episode>
) : RecyclerView.Adapter<EpisodesAdapter.EpisodeVieHolder>() {
    inner class EpisodeVieHolder(val binding: EpisodeItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeVieHolder {
        val binding = EpisodeItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return EpisodeVieHolder(binding)
    }

    override fun onBindViewHolder(holder: EpisodeVieHolder, position: Int) {
        val episode = episodes[position]
        holder.binding.episodeName.text = episode.title
        holder.binding.episodeThumbnail.load(episode.image) {
            error(R.drawable.missing_image)


            transformations(RoundedCornersTransformation(10f))
        }
    }

    override fun getItemCount(): Int = episodes.size
}