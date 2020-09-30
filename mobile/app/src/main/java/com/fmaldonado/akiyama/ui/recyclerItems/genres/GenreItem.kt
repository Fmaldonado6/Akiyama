package com.fmaldonado.akiyama.ui.recyclerItems.genres

import com.fmaldonado.akiyama.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.genre_item.view.*

class GenreItem(private val genre: String) : Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.containerView.genreText.text = genre
    }

    override fun getLayout() = R.layout.genre_item

}