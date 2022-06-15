package com.fmaldonado.akiyama.ui.common.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.fmaldonado.akiyama.data.models.content.Anime
import com.fmaldonado.akiyama.databinding.AnimeSearchItemBinding

class SearchResultAdapter(
    private var searchResult: List<Anime>,
    private val onClick: (Anime) -> Unit
) : RecyclerView.Adapter<SearchResultAdapter.SearchResultViewHolder>() {

    inner class SearchResultViewHolder(val binding: AnimeSearchItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val binding = AnimeSearchItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return SearchResultViewHolder(binding)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: List<Anime>) {
        searchResult = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        val anime = searchResult[position]

        holder.binding.animeName.text = anime.title
        holder.binding.animeStatus.text = anime.type
        holder.binding.root.setOnClickListener {
            onClick(anime)
        }
        holder.binding.animeImage.load(anime.image) {
            transformations(RoundedCornersTransformation(10f))
        }
    }

    override fun getItemCount(): Int = searchResult.size
}