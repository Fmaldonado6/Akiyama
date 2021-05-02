package com.fmaldonado.akiyama.ui.activities.animeDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.widget.TextView
import androidx.activity.viewModels
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
import com.xwray.groupie.GroupieAdapter
import dagger.hilt.android.AndroidEntryPoint

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
            binding.status.text = it.debut

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

    private fun setupEpisodesRecyler(episodes:List<Episode>) {
        val items = mutableListOf<EpisodesAdapter>()
        episodes.forEach {
            items.add(EpisodesAdapter(it))
        }

        val adapter = GroupieAdapter().apply { addAll(items) }

        binding.episodesList.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
        }
    }
}