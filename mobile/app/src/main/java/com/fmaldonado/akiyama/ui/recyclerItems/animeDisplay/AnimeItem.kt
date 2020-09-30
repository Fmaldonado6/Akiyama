package com.fmaldonado.akiyama.ui.recyclerItems.animeDisplay

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.util.Base64
import com.bumptech.glide.Glide
import com.fmaldonado.akiyama.R
import com.fmaldonado.akiyama.data.models.apiContent.AnimeModel
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.anime_display_recycler.view.*

class AnimeItem(val anime: AnimeModel, private val context: Context) : Item() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.containerView.animeTitle.text = anime.title
        val image = viewHolder.containerView.animeImage
        val byteArray = Base64.decode(anime.image, Base64.DEFAULT)
        Glide.with(context).asBitmap().load(byteArray).into(image)
    }

    override fun getLayout() = R.layout.anime_display_recycler
}