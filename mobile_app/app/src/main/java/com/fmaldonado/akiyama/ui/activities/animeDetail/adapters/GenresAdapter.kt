package com.fmaldonado.akiyama.ui.activities.animeDetail.adapters

import android.view.View
import com.fmaldonado.akiyama.R
import com.fmaldonado.akiyama.databinding.GenreItemBinding
import com.xwray.groupie.viewbinding.BindableItem

class GenresAdapter(private val genre: String) : BindableItem<GenreItemBinding>() {
    override fun bind(viewBinding: GenreItemBinding, position: Int) {
        viewBinding.genre.text = genre
    }

    override fun getLayout(): Int = R.layout.genre_item

    override fun initializeViewBinding(view: View): GenreItemBinding = GenreItemBinding.bind(view)
}