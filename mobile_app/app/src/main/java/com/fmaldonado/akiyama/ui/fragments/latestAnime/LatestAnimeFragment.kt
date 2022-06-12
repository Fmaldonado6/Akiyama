package com.fmaldonado.akiyama.ui.fragments.latestAnime

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fmaldonado.akiyama.data.models.content.Anime
import com.fmaldonado.akiyama.data.models.content.Episode
import com.fmaldonado.akiyama.databinding.FragmentLatestAnimeBinding
import com.fmaldonado.akiyama.ui.common.ParcelableKeys
import com.fmaldonado.akiyama.ui.common.Status
import com.fmaldonado.akiyama.ui.common.adapters.LatestAnimeAdapter

class LatestAnimeFragment : Fragment() {

    private lateinit var binding: FragmentLatestAnimeBinding
    private var title: String = "Titulo"
    private var isEpisodeList = false
    private var animeList: List<Anime>? = null
    private var episodeList: List<Episode>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val title = it.getString(ParcelableKeys.TITLE_PARCELABLE) ?: "Titulo"

            val animeArrayList = it.getParcelableArrayList<Anime>(
                ParcelableKeys.ANIME_PARCELABLE
            )

            val episodeArrayList = it.getParcelableArrayList<Episode>(
                ParcelableKeys.EPISODE_PARCELABLE
            )

            this.title = title
            this.animeList = animeArrayList
            this.episodeList = episodeArrayList
            isEpisodeList = episodeArrayList != null
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(
            title: String = "Titulo",
            episodes: List<Episode>? = null,
            animes: List<Anime>? = null
        ) =
            LatestAnimeFragment().apply {
                val bundle = Bundle()

                val episodeArrayList = if (episodes != null) ArrayList(episodes) else null
                val animesArrayList = if (animes != null) ArrayList(animes) else null
                bundle.putString(ParcelableKeys.TITLE_PARCELABLE, title)
                bundle.putParcelableArrayList(ParcelableKeys.EPISODE_PARCELABLE, episodeArrayList)
                bundle.putParcelableArrayList(ParcelableKeys.EPISODE_PARCELABLE, animesArrayList)
                arguments = bundle
            }
    }

    fun setContent(
        episodes: List<Episode>? = null,
        animes: List<Anime>? = null
    ) {
        this.animeList = animes
        this.episodeList = episodes
        isEpisodeList = this.episodeList != null
        setupRecycler()
    }

    fun setStatus(status: Status) {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLatestAnimeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.title.text = title
    }

    private fun setupRecycler() {
        var adapter: LatestAnimeAdapter =
            if (isEpisodeList)
                LatestAnimeAdapter(episodes = episodeList)
            else LatestAnimeAdapter(
                animes = animeList
            )

        binding.animeList.adapter = adapter
    }
}