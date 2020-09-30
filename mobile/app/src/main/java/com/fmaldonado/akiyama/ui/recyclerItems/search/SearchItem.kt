package com.fmaldonado.akiyama.ui.recyclerItems.search

import android.content.Context
import android.util.Base64
import com.bumptech.glide.Glide
import com.fmaldonado.akiyama.R
import com.fmaldonado.akiyama.data.models.apiContent.AnimeModel
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.anime_display_recycler.view.*

class SearchItem(val anime: AnimeModel, val context: Context) : Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.containerView.animeTitle.text = anime.title
        val image = viewHolder.containerView.animeImage
        Glide.with(context).load(anime.image).into(image)
    }

    override fun getLayout() = R.layout.search_item
}