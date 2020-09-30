package com.fmaldonado.akiyama.ui.recyclerItems.episodes

import com.fmaldonado.akiyama.R
import com.fmaldonado.akiyama.data.models.apiContent.EpisodeModel
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.episode_item.view.*

class EpisodeItem(val episodeModel: EpisodeModel) : Item() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.containerView.episodeTitle.text = "Episode " + episodeModel.episode.toInt()
    }

    override fun getLayout() = R.layout.episode_item
}