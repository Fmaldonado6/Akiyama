package com.fmaldonado.akiyama.ui.common.adapters

import android.util.Base64
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.fmaldonado.akiyama.R
import com.fmaldonado.akiyama.data.models.content.Anime
import com.fmaldonado.akiyama.data.models.content.Episode
import com.fmaldonado.akiyama.databinding.AnimeItemBinding
import com.xwray.groupie.viewbinding.BindableItem
import java.net.URI

class AnimeAdapter(
    val anime: Anime? = null,
    val episode: Episode? = null
) : BindableItem<AnimeItemBinding>() {

    override fun bind(viewBinding: AnimeItemBinding, position: Int) {

        var title = ""
        var url: String? = null
        if (anime != null) {
            url = anime.poster
            anime.title.let { title = it }
        } else if (episode != null) {
            url = episode.poster
            title = episode.title
        }

        var glideUrl = GlideUrl(
            url ?: "", LazyHeaders.Builder()
                .addHeader("User-Agent", "5")
                .build()
        )



        Glide.with(viewBinding.root)
            .load(glideUrl)
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