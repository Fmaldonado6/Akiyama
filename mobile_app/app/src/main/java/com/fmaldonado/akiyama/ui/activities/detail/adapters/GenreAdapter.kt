package com.fmaldonado.akiyama.ui.activities.detail.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fmaldonado.akiyama.databinding.GenreItemBinding

class GenreAdapter(
    private val genres: List<String>
) : RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {

    inner class GenreViewHolder(val binding: GenreItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val binding = GenreItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return GenreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val item = genres[position]
        holder.binding.genreChip.text = item
    }

    override fun getItemCount(): Int = genres.size
}