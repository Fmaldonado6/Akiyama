package com.fmaldonado.akiyama.ui.recyclerItems.servers

import com.fmaldonado.akiyama.R
import com.fmaldonado.akiyama.data.models.apiContent.ServerModel
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.episode_item.view.*
import kotlinx.android.synthetic.main.server_item.view.*

class ServerItem(val server: ServerModel) : Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.containerView.serverButton.text = server.id
    }

    override fun getLayout() = R.layout.server_item
}