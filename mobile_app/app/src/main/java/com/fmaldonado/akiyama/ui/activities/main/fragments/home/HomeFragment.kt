package com.fmaldonado.akiyama.ui.activities.main.fragments.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fmaldonado.akiyama.data.models.content.Anime
import com.fmaldonado.akiyama.data.models.content.Episode
import com.fmaldonado.akiyama.databinding.AnimeSectionBinding
import com.fmaldonado.akiyama.databinding.HomeFragmentBinding
import com.fmaldonado.akiyama.ui.activities.animeDetail.AnimeDetailActivity
import com.fmaldonado.akiyama.ui.activities.search.SearchActivity
import com.fmaldonado.akiyama.ui.common.ParcelableKeys
import com.fmaldonado.akiyama.ui.common.adapters.AnimeAdapter
import com.fmaldonado.akiyama.ui.common.fragments.serverBottomSheet.ServersBottomSheet
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getLatestEpisodes()
        viewModel.getLatestAnimes()
        viewModel.getLatestMovies()
        viewModel.getLatestOvas()
        viewModel.getLatestSpecials()

        viewModel.latestEpisodes.observe(viewLifecycleOwner, {
            setUpRecycler(binding.latestEpisode, it)
        })
        viewModel.latestAnimes.observe(viewLifecycleOwner, {
            setUpRecycler(binding.latestAnime, anime = it)
        })
        viewModel.latestMovies.observe(viewLifecycleOwner, {
            setUpRecycler(binding.latestMovies, anime = it)
        })
        viewModel.latestSpecials.observe(viewLifecycleOwner, {
            setUpRecycler(binding.latestSpecials, anime = it)
        })
        viewModel.latestOvas.observe(viewLifecycleOwner, {
            setUpRecycler(binding.latestOvas, anime = it)
        })

        viewModel.latestEpisodesStatus.observe(viewLifecycleOwner, { binding.episodesStatus = it })
        viewModel.latestAnimesStatus.observe(viewLifecycleOwner, { binding.animesStatus = it })
        viewModel.latestMoviesStatus.observe(viewLifecycleOwner, { binding.moviesStatus = it })
        viewModel.latestOvasStatus.observe(viewLifecycleOwner, { binding.ovasStatus = it })
        viewModel.latestSpecialsStatus.observe(viewLifecycleOwner, { binding.specialsStatus = it })

        binding.searchBar.setOnClickListener {
            val intent = Intent(context, SearchActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setUpRecycler(
        view: AnimeSectionBinding,
        episodes: List<Episode>? = null,
        anime: List<Anime>? = null
    ) {
        val items = mutableListOf<AnimeAdapter>()
        episodes?.forEach {
            items.add(AnimeAdapter(episode = it))
        }

        anime?.forEach {
            items.add(AnimeAdapter(anime = it))
        }

        val adapter = GroupieAdapter().apply { this.addAll(items) }
        adapter.setOnItemClickListener { item: Item<GroupieViewHolder>, view: View ->
            val animeItem = item as AnimeAdapter

            if (animeItem.anime != null) {
                val intent = Intent(context, AnimeDetailActivity::class.java)
                intent.putExtra(ParcelableKeys.ANIME_PARCELABLE, animeItem.anime)
                startActivity(intent)
            } else if (animeItem.episode != null) {
                ServersBottomSheet.newInstance(animeItem.episode)
                    .show(requireActivity().supportFragmentManager, this.tag)
            }

        }
        view.animeList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            this.adapter = adapter
        }

    }

}