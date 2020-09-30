package com.fmaldonado.akiyama.ui.activities.animeActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.fmaldonado.akiyama.R
import com.fmaldonado.akiyama.data.models.apiContent.AnimeModel
import com.fmaldonado.akiyama.data.models.apiContent.Status
import com.fmaldonado.akiyama.data.models.arguments.ActivitiesArguments
import com.fmaldonado.akiyama.ui.fragments.animeDisplay.AnimeDisplayViewModel
import com.fmaldonado.akiyama.ui.fragments.dialogs.bottomSheet.ServersBottomSheet
import com.fmaldonado.akiyama.ui.recyclerItems.animeDisplay.AnimeItem
import com.fmaldonado.akiyama.ui.recyclerItems.episodes.EpisodeItem
import com.fmaldonado.akiyama.ui.recyclerItems.genres.GenreItem
import com.google.gson.Gson
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.activity_anime.*
import kotlinx.android.synthetic.main.activity_anime.errorGroup
import kotlinx.android.synthetic.main.activity_anime.progressBar
import kotlinx.android.synthetic.main.activity_anime.toolbar
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class AnimeActivity : AppCompatActivity(), KodeinAware {

    override val kodein by closestKodein()

    private val factory by instance<AnimeActivityViewModelFactory>()
    private lateinit var viewModel: AnimeActivityViewModel
    private lateinit var anime: AnimeModel
    private var getInfo = false
    private val serverBottomSheet = ServersBottomSheet()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(AnimeActivityViewModel::class.java)

        setContentView(R.layout.activity_anime)
        val animeString = intent.getStringExtra(ActivitiesArguments.Animes.value)
        val gson = Gson()
        anime = gson.fromJson(animeString, AnimeModel::class.java)
        getInfo = intent.getBooleanExtra(ActivitiesArguments.GetInfo.value, false)
        Log.d("Info",getInfo.toString())
        synopsis.text = anime.synopsis
        toolbar.title = anime.title

        if (!getInfo) {
            displayImage()
            setAnimeInfo()
            changeStatus(Status.Loaded.value)
        } else {
            displayImage(false)
            getAnimeInfo()
        }

    }

    private fun getAnimeInfo() {
        viewModel.getAnimeInfo(anime.title)

        viewModel.currentStatus.observe(this, Observer {
            changeStatus(it)
        })

        viewModel.animeInfo.observe(this, Observer {
            val anime = it.info
            this.anime = anime[0]
            setAnimeInfo()
        })

        retryButton.setOnClickListener {
            viewModel.getAnimeInfo(anime.title)
        }
    }

    private fun setAnimeInfo() {
        synopsis.text = anime.synopsis
        showGenres()
        showEpisodes()
    }

    private fun displayImage(base64: Boolean = true) {
        val image = anime_image
        if (base64) {
            val byteArray = Base64.decode(anime.image, Base64.DEFAULT)
            Glide.with(applicationContext).asBitmap().load(byteArray).into(image)
        } else {
            Glide.with(applicationContext).load(anime.image).into(image)
        }
        image.scrollTo(0, -1)
        image.isVerticalFadingEdgeEnabled = true
        image.setFadingEdgeLength(400)
    }

    private fun showGenres() {
        val items = mutableListOf<GenreItem>()

        anime.genres.forEach {
            items.add(GenreItem(it))
        }

        val adapter = GroupAdapter<GroupieViewHolder>().apply {
            this.addAll(items)
        }

        genres.apply {
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            this.adapter = adapter
        }

    }

    private fun showEpisodes() {
        val items = mutableListOf<EpisodeItem>()

        anime.episodes.forEachIndexed { index, episodeModel ->
            if (index == 0)
                return@forEachIndexed
            items.add(EpisodeItem(episodeModel))
        }

        val adapter = GroupAdapter<GroupieViewHolder>().apply {
            this.addAll(items)
        }

        adapter.setOnItemClickListener { item: Item<GroupieViewHolder>, view: View ->
            val episode = item as EpisodeItem
            val bundle = Bundle()
            bundle.putString(ActivitiesArguments.EpisodeId.value, episode.episodeModel.id)
            serverBottomSheet.arguments = bundle
            serverBottomSheet.show(supportFragmentManager, "serverSheet")
        }

        episodes.apply {
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            this.adapter = adapter
        }

    }

    private fun changeStatus(status: Int) {

        when (status) {
            Status.Loading.value -> {
                progressBar.visibility = View.VISIBLE
                animeInfo.visibility = View.INVISIBLE
                errorGroup.visibility = View.INVISIBLE

            }
            Status.Loaded.value -> {
                progressBar.visibility = View.INVISIBLE
                animeInfo.visibility = View.VISIBLE
                errorGroup.visibility = View.INVISIBLE
            }
            Status.Error.value -> {
                errorGroup.visibility = View.VISIBLE
                progressBar.visibility = View.INVISIBLE
                animeInfo.visibility = View.INVISIBLE
            }
        }

    }

}