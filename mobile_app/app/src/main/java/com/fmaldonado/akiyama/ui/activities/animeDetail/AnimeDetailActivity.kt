package com.fmaldonado.akiyama.ui.activities.animeDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.fmaldonado.akiyama.R
import com.fmaldonado.akiyama.data.models.content.Anime
import com.fmaldonado.akiyama.data.models.content.Episode
import com.fmaldonado.akiyama.databinding.ActivityAnimeDetailBinding
import com.fmaldonado.akiyama.ui.activities.animeDetail.adapters.EpisodesAdapter
import com.fmaldonado.akiyama.ui.activities.animeDetail.adapters.GenresAdapter
import com.fmaldonado.akiyama.ui.common.ParcelableNames
import com.fmaldonado.akiyama.ui.common.fragments.serverBottomSheet.ServersBottomSheet
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnimeDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAnimeDetailBinding
    private var anime: Anime? = null
    private var expandedSynopsis = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        anime = intent?.extras?.getParcelable(ParcelableNames.Anime.value)
        anime?.let {
            val byteArray = Base64.decode(it.poster, Base64.DEFAULT)
            binding.animeTitle.text = it.title
            binding.synopsis.text = it.synopsis
            binding.status.text =
                if (it.debut == "En emision")
                    resources.getString(R.string.airing_text)
                else
                    resources.getString(R.string.finished_text)

            setupGenresRecycler(it.genres)
            setupEpisodesRecyler(it.episodes)
            Glide.with(this)
                .load(byteArray)
                .centerCrop()
                .into(binding.animeBackground)

            Glide.with(this)
                .load(byteArray)
                .transform(CenterCrop(), RoundedCorners(20))
                .into(binding.animeCover)
        }

        binding.backButton.setOnClickListener {
            finish()
        }

        binding.moreButton.setOnClickListener {
            binding.synopsis.maxLines = if (expandedSynopsis) 3 else Integer.MAX_VALUE
            expandedSynopsis = !expandedSynopsis
            binding.moreButton.text =
                if (expandedSynopsis) resources.getString(R.string.less_text) else resources.getString(
                    R.string.more_text
                )
        }
    }

    private fun setupGenresRecycler(genres: List<String>) {
        val items = mutableListOf<GenresAdapter>()
        genres.forEach {
            items.add(GenresAdapter(it))
        }

        val adapter = GroupieAdapter().apply { addAll(items) }

        binding.genresList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            this.adapter = adapter
        }
    }

    private fun setupEpisodesRecyler(episodes: List<Episode>) {
        val items = mutableListOf<EpisodesAdapter>()
        episodes.forEach {
            if (it.nextEpisodeDate == null && episodes.first() == it)
                return@forEach
            items.add(EpisodesAdapter(it))
        }

        val adapter = GroupieAdapter().apply { addAll(items) }

        adapter.setOnItemClickListener { item: Item<GroupieViewHolder>, view: View ->
            val episodeItem = item as EpisodesAdapter

            if (episodeItem.clickable)
                ServersBottomSheet.newInstance(episodeItem.episode).apply {
                    this.show(supportFragmentManager, this.tag)
                }
        }

        binding.episodesList.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
        }
    }
}