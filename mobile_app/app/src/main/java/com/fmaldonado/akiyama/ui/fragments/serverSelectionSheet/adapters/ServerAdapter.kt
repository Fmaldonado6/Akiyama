package com.fmaldonado.akiyama.ui.fragments.serverSelectionSheet.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fmaldonado.akiyama.data.models.content.Server
import com.fmaldonado.akiyama.databinding.ServerItemBinding

class ServerAdapter(
    private val servers: List<Server>,
    private val onClick: (Server) -> Unit
) : RecyclerView.Adapter<ServerAdapter.ServerViewHolder>() {

    inner class ServerViewHolder(val binding: ServerItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServerViewHolder {
        val binding = ServerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ServerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ServerViewHolder, position: Int) {
        val item = servers[position]
        holder.binding.serverName.text = item.title
    }

    override fun getItemCount(): Int = servers.size

}