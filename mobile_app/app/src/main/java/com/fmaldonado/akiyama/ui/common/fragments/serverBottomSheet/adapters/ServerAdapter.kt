package com.fmaldonado.akiyama.ui.common.fragments.serverBottomSheet.adapters

import android.view.View
import com.fmaldonado.akiyama.R
import com.fmaldonado.akiyama.data.models.content.Server
import com.fmaldonado.akiyama.databinding.ServerItemBinding
import com.xwray.groupie.viewbinding.BindableItem

class ServerAdapter(val server: Server) : BindableItem<ServerItemBinding>() {
    override fun bind(viewBinding: ServerItemBinding, position: Int) {
        viewBinding.serverName.text = server.title
    }

    override fun getLayout(): Int = R.layout.server_item

    override fun initializeViewBinding(view: View): ServerItemBinding = ServerItemBinding.bind(view)

}