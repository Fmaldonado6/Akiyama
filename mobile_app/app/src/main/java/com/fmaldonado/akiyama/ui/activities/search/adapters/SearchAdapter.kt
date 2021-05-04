package com.fmaldonado.akiyama.ui.activities.search.adapters

import android.util.Base64
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.fmaldonado.akiyama.R
import com.fmaldonado.akiyama.data.models.content.Anime
import com.fmaldonado.akiyama.data.models.content.Episode
import com.fmaldonado.akiyama.databinding.AnimeItemBinding
import com.fmaldonado.akiyama.databinding.SearchItemBinding
import com.xwray.groupie.viewbinding.BindableItem

class SearchAdapter(
    val anime: Anime,
) : BindableItem<SearchItemBinding>() {

    override fun bind(viewBinding: SearchItemBinding, position: Int) {

        val byteArray = Base64.decode(anime.poster, Base64.DEFAULT)
        val title = anime.title

        Glide.with(viewBinding.root)
            .load(byteArray)
            .transform(CenterCrop(), RoundedCorners(20))
            .into(viewBinding.animeImage)
        viewBinding.animeTitle.apply {
            text = title
            isSelected = true
        }

    }
    override fun getLayout(): Int = R.layout.search_item
    override fun initializeViewBinding(view: View): SearchItemBinding = SearchItemBinding.bind(view)
}