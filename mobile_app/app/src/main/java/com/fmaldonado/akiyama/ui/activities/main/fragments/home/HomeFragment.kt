package com.fmaldonado.akiyama.ui.activities.main.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.fmaldonado.akiyama.R
import com.fmaldonado.akiyama.databinding.FragmentHomeBinding
import com.fmaldonado.akiyama.ui.fragments.latestAnime.LatestAnimeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    private lateinit var sections: List<LatestAnimeFragment>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createSections()


        LatestAnimeSections.values().forEach {
            viewModel.getAnimeSection(true, it)
        }

        viewModel.getLatestAnimeData().observe(viewLifecycleOwner) {
            val section = sections[it.first.ordinal]
            section.setContent(it.second)
        }

        viewModel.getLatestAnimeStatus().observe(viewLifecycleOwner) {
            val section = sections[it.first.ordinal]
            section.setStatus(it.second)
        }

    }

    private fun createSections() {
        sections = LatestAnimeSections.values().map {
            createLatestAnimeFragment(it.title)
        }
    }

    private fun createLatestAnimeFragment(title: String): LatestAnimeFragment {
        val fragment = LatestAnimeFragment.newInstance(title = title)
        childFragmentManager.beginTransaction()
            .add(binding.latestAnimeContainer.id, fragment)
            .commit()

        return fragment
    }

}

enum class LatestAnimeSections(val title: String) {
    LatestEpisodes("Latest Episodes"),
    LatestAnime("Latest Anime"),
    LatestMovies("Latest Movies"),
    LatestOvas("Latest Ovas"),
    LatestSpecials("Latest Specials"),

}