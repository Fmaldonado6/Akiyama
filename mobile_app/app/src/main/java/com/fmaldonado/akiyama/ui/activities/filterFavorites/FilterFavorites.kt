package com.fmaldonado.akiyama.ui.activities.filterFavorites

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.fmaldonado.akiyama.R
import com.fmaldonado.akiyama.data.models.content.Anime
import com.fmaldonado.akiyama.databinding.ActivityFilterFavoritesBinding
import com.fmaldonado.akiyama.ui.activities.animeDetail.AnimeDetailActivity
import com.fmaldonado.akiyama.ui.common.ParcelableKeys
import com.fmaldonado.akiyama.ui.common.Status
import com.fmaldonado.akiyama.ui.common.adapters.AnimeListAdapter
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class FilterFavorites : AppCompatActivity() {

    private val viewModel: FilterFavoritesViewModel by viewModels()
    private lateinit var binding: ActivityFilterFavoritesBinding
    private var favorites: List<Anime> = mutableListOf<Anime>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilterFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener { finish() }

        viewModel.favorites.observe(this, {
            favorites = it
            when (it.isEmpty()) {
                true -> viewModel.currentStatus.value = Status.Empty
                else -> {
                    viewModel.currentStatus.value = Status.Loaded
                    setupRecycler(it)
                }
            }
        })

        viewModel.currentStatus.observe(this, { binding.currentStatus = it })

        binding.searchBar.requestFocus()

        binding.searchBar.addTextChangedListener {
            setupRecycler(favorites, it.toString())
        }
    }


    private fun setupRecycler(searchResults: List<Anime>, filter: String = "") {
        val items = mutableListOf<AnimeListAdapter>()
        searchResults.forEach {
            if (filter.isEmpty() || it.title.contains(filter, true))
                items.add(AnimeListAdapter(it))
        }

        if (items.isEmpty()) {
            viewModel.currentStatus.value = Status.Empty
            return
        }

        viewModel.currentStatus.value = Status.Loaded

        val adapter = GroupieAdapter().apply { addAll(items) }

        adapter.setOnItemClickListener { item: Item<GroupieViewHolder>, view: View ->
            val animeItem = item as AnimeListAdapter
            val intent = Intent(this, AnimeDetailActivity::class.java)
            intent.putExtra(ParcelableKeys.ANIME_PARCELABLE, animeItem.anime)
            startActivity(intent)
        }

        binding.searchResults.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
        }
    }
}