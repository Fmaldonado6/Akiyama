package com.fmaldonado.akiyama.ui.fragments.latestAnime

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.fmaldonado.akiyama.data.models.content.Anime
import com.fmaldonado.akiyama.data.models.content.Episode
import com.fmaldonado.akiyama.data.models.content.MainScreenContent
import com.fmaldonado.akiyama.data.models.content.MainScreenContentType
import com.fmaldonado.akiyama.databinding.FragmentLatestAnimeBinding
import com.fmaldonado.akiyama.ui.activities.detail.DetailActivity
import com.fmaldonado.akiyama.ui.common.ParcelableKeys
import com.fmaldonado.akiyama.ui.common.Status
import com.fmaldonado.akiyama.ui.common.adapters.LatestAnimeAdapter
import com.fmaldonado.akiyama.ui.fragments.serverSelectionSheet.ServerSelectionBottomSheet

class LatestAnimeFragment : Fragment() {

    private lateinit var binding: FragmentLatestAnimeBinding
    private var title: String = "Titulo"
    private var smallSection = false
    private var animeList: List<MainScreenContent>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val title = it.getString(ParcelableKeys.TITLE_PARCELABLE) ?: "Titulo"
            smallSection = it.getBoolean(ParcelableKeys.SMALL_SECTION_PARCEL)
            this.title = title
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(
            title: String = "Titulo",
            smallSection: Boolean = false
        ) =
            LatestAnimeFragment().apply {
                val bundle = Bundle()
                bundle.putString(ParcelableKeys.TITLE_PARCELABLE, title)
                bundle.putBoolean(ParcelableKeys.SMALL_SECTION_PARCEL, smallSection)

                arguments = bundle
            }
    }

    private fun setSmall() {
        val smallHeight = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            100f,
            resources.displayMetrics
        )
        val listParams = binding.animeList.layoutParams
        val statusParams = binding.progressBar.layoutParams

        listParams.height = smallHeight.toInt()
        statusParams.height = smallHeight.toInt()

        binding.animeList.layoutParams = listParams
        binding.progressBar.layoutParams = statusParams
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
        if (smallSection) setSmall()
    }

    private fun setupRecycler() {
        val adapter = LatestAnimeAdapter(animeList ?: listOf()) {
            if (it.type == MainScreenContentType.Anime)
                changeToDetail(it)
            else
                openServerBottomSheet(it)
        }
        binding.animeList.adapter = adapter
    }

    private fun changeToDetail(anime: MainScreenContent) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(ParcelableKeys.ANIME_PARCELABLE, anime)
        startActivity(intent)
    }

    private fun openServerBottomSheet(episode: MainScreenContent) {
        val sheet = ServerSelectionBottomSheet.newInstance(Episode(episode.id))
        sheet.show(this.childFragmentManager, ServerSelectionBottomSheet.TAG)
    }
}