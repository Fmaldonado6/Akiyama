package com.fmaldonado.akiyama.ui.fragments.home

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.fmaldonado.akiyama.R
import com.fmaldonado.akiyama.data.models.apiContent.AnimeModel
import com.fmaldonado.akiyama.data.models.apiContent.DataTypes
import com.fmaldonado.akiyama.data.models.apiContent.EpisodeModel
import com.fmaldonado.akiyama.data.models.apiContent.Status
import com.fmaldonado.akiyama.ui.fragments.animeDisplay.AnimeDisplayFragment
import com.fmaldonado.akiyama.ui.recyclerItems.animeDisplay.AnimeItem
import com.fmaldonado.akiyama.ui.recyclerItems.animeDisplay.EpisodeItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.anime_display.view.*

import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class HomeFragment : Fragment(), KodeinAware {

    override val kodein by closestKodein()

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModelFactory by instance<HomeFragmentViewModelFactory>()

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
        if (savedInstanceState == null) {
            val episodeDisplay =
                AnimeDisplayFragment.newInstance("Latest Episodes", DataTypes.Episodes.value)
            val animeDisplay =
                AnimeDisplayFragment.newInstance("Latest Animes", DataTypes.Animes.value)
            val moviesDisplay =
                AnimeDisplayFragment.newInstance("Latest Movies", DataTypes.Movies.value)
            val specialsDisplay =
                AnimeDisplayFragment.newInstance("Latest Specials", DataTypes.Specials.value)
            val ovasDisplay =
                AnimeDisplayFragment.newInstance("Latest Ovas", DataTypes.Ovas.value)

            childFragmentManager.beginTransaction().add(R.id.mainLayout, episodeDisplay).commit()
            childFragmentManager.beginTransaction().add(R.id.mainLayout, animeDisplay).commit()
            childFragmentManager.beginTransaction().add(R.id.mainLayout, moviesDisplay).commit()
            childFragmentManager.beginTransaction().add(R.id.mainLayout, specialsDisplay).commit()
            childFragmentManager.beginTransaction().add(R.id.mainLayout, ovasDisplay).commit()


        }
    }


}