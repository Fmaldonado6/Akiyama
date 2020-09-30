package com.fmaldonado.akiyama.ui.recyclerItems.animeDisplay

import android.content.Context
import android.util.Base64
import android.util.Log
import com.bumptech.glide.Glide
import com.fmaldonado.akiyama.R
import com.fmaldonado.akiyama.data.models.apiContent.EpisodeModel
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.anime_display_recycler.view.*

class EpisodeItem(val episode: EpisodeModel, private val context: Context) : Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.containerView.animeTitle.text = episode.title
        val image = viewHolder.containerView.animeImage
        val byteArray = Base64.decode(episode.image, Base64.DEFAULT)
        Glide.with(context).asBitmap().load(byteArray).into(image)
    }

    override fun getLayout() = R.layout.anime_display_recycler
}