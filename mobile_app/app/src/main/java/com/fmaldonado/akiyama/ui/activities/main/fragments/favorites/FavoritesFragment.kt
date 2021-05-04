package com.fmaldonado.akiyama.ui.activities.main.fragments.favorites

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fmaldonado.akiyama.R
import com.fmaldonado.akiyama.data.models.content.Anime
import com.fmaldonado.akiyama.databinding.FavoritesFragmentBinding
import com.fmaldonado.akiyama.ui.activities.animeDetail.AnimeDetailActivity
import com.fmaldonado.akiyama.ui.activities.filterFavorites.FilterFavorites
import com.fmaldonado.akiyama.ui.common.ParcelableKeys
import com.fmaldonado.akiyama.ui.common.Status
import com.fmaldonado.akiyama.ui.common.adapters.AnimeListAdapter
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private val viewModel: FavoritesViewModel by viewModels()

    private lateinit var binding: FavoritesFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FavoritesFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getFavorites()
        viewModel.favorites.observe(viewLifecycleOwner, {
            when (it.isEmpty()) {
                true -> viewModel.currentStatus.value = Status.Empty
                else -> {
                    viewModel.currentStatus.value = Status.Loaded
                    setupRecycler(it)
                }
            }
        })

        viewModel.currentStatus.observe(viewLifecycleOwner, { binding.currentStatus = it })

        binding.searchBar.setOnClickListener {
            val intent = Intent(context, FilterFavorites::class.java)
            startActivity(intent)
        }
    }

    private fun setupRecycler(animes: List<Anime>) {
        val items = mutableListOf<AnimeListAdapter>()
        animes.forEach {
            items.add(AnimeListAdapter(it))
        }

        val adapter = GroupieAdapter().apply { addAll(items) }

        adapter.setOnItemClickListener { item: Item<GroupieViewHolder>, view: View ->
            val animeItem = item as AnimeListAdapter
            val intent = Intent(context, AnimeDetailActivity::class.java)
            intent.putExtra(ParcelableKeys.ANIME_PARCELABLE, animeItem.anime)
            startActivity(intent)
        }

        binding.favoritesList.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
        }
    }


}