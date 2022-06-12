package com.fmaldonado.akiyama.ui.activities.main.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
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

    private var sections: MutableList<LatestAnimeFragment> = mutableListOf()

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
            viewModel.getAnimeSection(false, it)
        }

        viewModel.getLatestAnimeData().forEachIndexed { index, item ->
            item.observe(viewLifecycleOwner) {
                val section = sections[index]
                section.setContent(it)
            }
        }

        viewModel.getLatestAnimeStatus().forEachIndexed { index, item ->
            item.observe(viewLifecycleOwner) {
                val section = sections[index]
                section.setStatus(it)
            }
        }

    }

    private fun createSections() {

        childFragmentManager.fragments.forEach {
            childFragmentManager.beginTransaction().remove(it).commit()
        }

        sections.clear()
        sections = LatestAnimeSections.values().map {
            createLatestAnimeFragment(it.title)
        }.toMutableList()
    }

    private fun createLatestAnimeFragment(title: String): LatestAnimeFragment {
        val fragment = LatestAnimeFragment.newInstance(title = title)
        childFragmentManager.beginTransaction()
            .add(binding.latestAnimeContainer.id, fragment, title)
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