package com.fmaldonado.akiyama.ui.activities.animeDetail.adapters

import android.content.res.Resources
import android.view.View
import com.fmaldonado.akiyama.R
import com.fmaldonado.akiyama.data.models.content.Episode
import com.fmaldonado.akiyama.databinding.EpisodeItemBinding
import com.xwray.groupie.viewbinding.BindableItem

class EpisodesAdapter(private val episode: Episode) : BindableItem<EpisodeItemBinding>() {
    override fun bind(viewBinding: EpisodeItemBinding, position: Int) {
        val text = if (episode.nextEpisodeDate != null) {
            val textLabel = viewBinding.root.context.getString(R.string.next_episode_text)
            "$textLabel: ${episode.nextEpisodeDate}"
        } else {
            val textLabel = viewBinding.root.context.getString(R.string.episodes_label_text)
            "$textLabel # ${episode.episode}"
        }

        viewBinding.episode.text = text
    }

    override fun getLayout(): Int = R.layout.episode_item

    override fun initializeViewBinding(view: View): EpisodeItemBinding =
        EpisodeItemBinding.bind(view)
}