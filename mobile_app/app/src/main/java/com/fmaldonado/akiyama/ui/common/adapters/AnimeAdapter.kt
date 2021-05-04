package com.fmaldonado.akiyama.ui.common.adapters

import android.util.Base64
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.fmaldonado.akiyama.R
import com.fmaldonado.akiyama.data.models.content.Anime
import com.fmaldonado.akiyama.data.models.content.Episode
import com.fmaldonado.akiyama.databinding.AnimeItemBinding
import com.xwray.groupie.viewbinding.BindableItem

class AnimeAdapter(
    val anime: Anime? = null,
    val episode: Episode? = null
) : BindableItem<AnimeItemBinding>() {

    override fun bind(viewBinding: AnimeItemBinding, position: Int) {

        var title = ""
        lateinit var byteArray: ByteArray

        if (anime != null) {
            byteArray = Base64.decode(anime.poster, Base64.DEFAULT)
            anime.title.let { title = it }
        } else if (episode != null) {
            byteArray = Base64.decode(episode.poster, Base64.DEFAULT)
            title = episode.title
        }

        Glide.with(viewBinding.root)
            .load(byteArray)
            .transform(CenterCrop(), RoundedCorners(20))
            .into(viewBinding.imageView)
        viewBinding.animeTitle.apply {
            text = title
            isSelected = true
        }

    }

    override fun getLayout(): Int = R.layout.anime_item
    override fun initializeViewBinding(view: View): AnimeItemBinding = AnimeItemBinding.bind(view)
}