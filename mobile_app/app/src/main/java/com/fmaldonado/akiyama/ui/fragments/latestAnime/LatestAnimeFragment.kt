package com.fmaldonado.akiyama.ui.fragments.latestAnime

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fmaldonado.akiyama.data.models.content.Anime
import com.fmaldonado.akiyama.data.models.content.Episode
import com.fmaldonado.akiyama.data.models.content.MainScreenContent
import com.fmaldonado.akiyama.databinding.FragmentLatestAnimeBinding
import com.fmaldonado.akiyama.ui.common.ParcelableKeys
import com.fmaldonado.akiyama.ui.common.Status
import com.fmaldonado.akiyama.ui.common.adapters.LatestAnimeAdapter

class LatestAnimeFragment : Fragment() {

    private lateinit var binding: FragmentLatestAnimeBinding
    private var title: String = "Titulo"
    private var animeList: List<MainScreenContent>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val title = it.getString(ParcelableKeys.TITLE_PARCELABLE) ?: "Titulo"
            this.title = title
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(
            title: String = "Titulo",
        ) =
            LatestAnimeFragment().apply {
                val bundle = Bundle()
                bundle.putString(ParcelableKeys.TITLE_PARCELABLE, title)
                arguments = bundle
            }
    }


    fun setContent(
        animes: List<MainScreenContent>? = null
    ) {
        this.animeList = animes
        setupRecycler()
    }

    fun setStatus(status: Status) {
        binding.status = status
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
        binding.status = Status.Loading
    }

    private fun setupRecycler() {
        val adapter = LatestAnimeAdapter(animeList ?: listOf())
        binding.animeList.adapter = adapter
    }
}