package com.fmaldonado.akiyama.ui.activities.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.fmaldonado.akiyama.R
import com.fmaldonado.akiyama.data.models.content.Anime
import com.fmaldonado.akiyama.data.models.content.Episode
import com.fmaldonado.akiyama.data.models.content.MainScreenContent
import com.fmaldonado.akiyama.databinding.ActivityDetailBinding
import com.fmaldonado.akiyama.ui.activities.detail.adapters.EpisodesAdapter
import com.fmaldonado.akiyama.ui.activities.detail.adapters.GenreAdapter
import com.fmaldonado.akiyama.ui.activities.watch.WatchActivity
import com.fmaldonado.akiyama.ui.common.ParcelableKeys
import com.fmaldonado.akiyama.ui.fragments.serverSelectionSheet.ServerSelectionBottomSheet
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModels()
    private var expandedSynopsis = false
    private lateinit var anime: Anime

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val content =
            intent.getParcelableExtra<MainScreenContent>(ParcelableKeys.MAIN_CONTENT_PARCELABLE)
        val anime = intent.getParcelableExtra<Anime>(ParcelableKeys.ANIME_PARCELABLE)

        content?.let {
            binding.cover.load(it.image)
            binding.poster.load(it.image) {
                transformations(RoundedCornersTransformation(10f))
            }
            binding.title.text = it.title
            this.anime = Anime(id = it.id)
            viewModel.getAnimeInfo(this.anime)
        }

        anime?.let {
            this.anime = it
            binding.cover.load(it.image)
            binding.poster.load(it.image) {
                transformations(RoundedCornersTransformation(10f))
            }
            binding.title.text = it.title
            viewModel.getAnimeInfo(it)
        }

        viewModel.getAnimeInfoData().observe(this) {
            this.anime = it
            binding.description.text = it.synopsis
            binding.animeStatus.text = it.status
            setupEpisodes(it.episodes)
            setupGenres(it.genres)
        }



        viewModel.getStatus().observe(this) {
            binding.status = it
        }

        viewModel.getIsFavorite().observe(this) {
            binding.isFavorite = it
        }

        binding.favoriteButton.setOnClickListener {
            viewModel.addToFavorites(this.anime)

            val text = if (viewModel.getIsFavorite().value!!)
                resources.getString(R.string.removed_from_favorites_text)
            else
                resources.getString(R.string.added_to_favorites_text)

            Snackbar.make(binding.favoriteButton, text, Snackbar.LENGTH_SHORT).show()

        }

        binding.errorLayout.retry.setOnClickListener {
            viewModel.getAnimeInfo(this.anime)
        }

        binding.moreButton.setOnClickListener {
            expandedSynopsis = !expandedSynopsis
            binding.description.maxLines = if (expandedSynopsis) Integer.MAX_VALUE else 3
            binding.moreButton.text =
                if (expandedSynopsis)
                    resources.getString(R.string.less_text)
                else
                    resources.getString(R.string.more_text)
        }

        binding.toolbar.setNavigationOnClickListener { finish() }

        WindowCompat.setDecorFitsSystemWindows(window, false)

    }

    private fun setupGenres(genres: List<String>) {
        val adapter = GenreAdapter(genres)
        binding.genres.adapter = adapter
    }

    private fun setupEpisodes(episodes: List<Episode>) {

        val adapter = EpisodesAdapter(episodes) {
            val sheet = ServerSelectionBottomSheet.newInstance(it)
            sheet.show(supportFragmentManager, ServerSelectionBottomSheet.TAG)
        }
        binding.episodeList.adapter = adapter
    }
}