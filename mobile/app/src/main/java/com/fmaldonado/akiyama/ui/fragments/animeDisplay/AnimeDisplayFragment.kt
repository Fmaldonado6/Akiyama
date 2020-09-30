package com.fmaldonado.akiyama.ui.fragments.animeDisplay

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.fmaldonado.akiyama.R
import com.fmaldonado.akiyama.data.models.apiContent.AnimeModel
import com.fmaldonado.akiyama.data.models.apiContent.DataTypes
import com.fmaldonado.akiyama.data.models.apiContent.EpisodeModel
import com.fmaldonado.akiyama.data.models.apiContent.Status
import com.fmaldonado.akiyama.data.models.arguments.ActivitiesArguments
import com.fmaldonado.akiyama.ui.activities.animeActivity.AnimeActivity
import com.fmaldonado.akiyama.ui.fragments.dialogs.bottomSheet.ServersBottomSheet
import com.fmaldonado.akiyama.ui.recyclerItems.animeDisplay.AnimeItem
import com.fmaldonado.akiyama.ui.recyclerItems.animeDisplay.EpisodeItem
import com.google.gson.Gson
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.anime_display.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class AnimeDisplayFragment : Fragment(), KodeinAware {
    override val kodein by closestKodein()

    private var title: String = ""
    private var dataType: Int = 0
    private val factory by instance<AnimeDisplayViewModelFactory>()

    companion object {
        fun newInstance(title: String, dataType: Int): AnimeDisplayFragment {
            val animeDisplay = AnimeDisplayFragment()

            val arguments = Bundle()

            arguments.putString(ActivitiesArguments.Title.value, title)
            arguments.putInt(ActivitiesArguments.DataType.value, dataType)

            animeDisplay.arguments = arguments

            return animeDisplay
        }
    }

    private lateinit var viewModel: AnimeDisplayViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.anime_display, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(AnimeDisplayViewModel::class.java)
        title = arguments?.getString(ActivitiesArguments.Title.value, "").toString()
        dataType = arguments?.getInt(ActivitiesArguments.DataType.value, 0)!!
        displayTitle.text = title
        initializeUi()
        getData()
        observeData()
    }

    private fun getData() {
        viewModel.getData(dataType)
    }

    private fun observeData() {
        when (dataType) {
            DataTypes.Episodes.value -> viewModel.episodes.observe(
                viewLifecycleOwner,
                Observer { setUpEpisodeRecycler(it.episodes) })
            DataTypes.Animes.value -> viewModel.animes.observe(
                viewLifecycleOwner,
                Observer { setUpRecycler(it.tv) })
            DataTypes.Specials.value -> viewModel.specials.observe(
                viewLifecycleOwner,
                Observer { setUpRecycler(it.specials) })
            DataTypes.Ovas.value -> viewModel.ovas.observe(
                viewLifecycleOwner,
                Observer { setUpRecycler(it.ovas) })
            DataTypes.Movies.value -> viewModel.movies.observe(
                viewLifecycleOwner,
                Observer { setUpRecycler(it.movies) })
        }
    }

    private fun setUpEpisodeRecycler(list: List<EpisodeModel>) {

        val items = mutableListOf<EpisodeItem>()


        list.forEach {
            items.add(EpisodeItem(it, requireContext()))
        }

        val adapter = GroupAdapter<GroupieViewHolder>().apply {
            this.addAll(items)
        }

        adapter.setOnItemClickListener { item: Item<GroupieViewHolder>, view: View ->
            val serverBottomSheet = ServersBottomSheet()

            val episodeItem = item as EpisodeItem
            val bundle = Bundle()
            val gson = Gson()
            val json = gson.toJson(episodeItem.episode.servers)
            bundle.putString(ActivitiesArguments.Servers.value, json)
            serverBottomSheet.arguments = bundle
            serverBottomSheet.show(childFragmentManager, "serverSheet")
        }

        recycler.apply {
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            this.adapter = adapter
        }
    }

    private fun setUpRecycler(list: List<AnimeModel>) {

        val items = mutableListOf<AnimeItem>()

        list.forEach {
            items.add(AnimeItem(it, requireContext()))
        }

        val adapter = GroupAdapter<GroupieViewHolder>().apply {
            this.addAll(items)
        }

        adapter.setOnItemClickListener { item: Item<GroupieViewHolder>, view: View ->
            val item = item as AnimeItem
            val intent = Intent(activity, AnimeActivity::class.java)
            val gson = Gson()
            val json = gson.toJson(item.anime)
            intent.putExtra(ActivitiesArguments.Animes.value, json)
            activity?.startActivity(intent)

        }

        recycler.apply {
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            this.adapter = adapter
        }

    }

    private fun initializeUi() {

        viewModel.status.observe(viewLifecycleOwner, Observer {
            changeStatus(it)
        })

        retryButton.setOnClickListener {
            getData()
        }

    }

    private fun changeStatus(status: Int) {

        when (status) {
            Status.Loading.value -> {
                progressBar.visibility = View.VISIBLE
                recycler.visibility = View.INVISIBLE
                errorGroup.visibility = View.INVISIBLE

            }
            Status.Loaded.value -> {
                progressBar.visibility = View.INVISIBLE
                recycler.visibility = View.VISIBLE
                errorGroup.visibility = View.INVISIBLE
            }
            Status.Error.value -> {
                errorGroup.visibility = View.VISIBLE
                progressBar.visibility = View.INVISIBLE
                recycler.visibility = View.INVISIBLE
            }
        }

    }

}