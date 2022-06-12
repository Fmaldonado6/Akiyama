package com.fmaldonado.akiyama.ui.activities.main.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fmaldonado.akiyama.data.models.content.Anime
import com.fmaldonado.akiyama.data.models.content.Episode
import com.fmaldonado.akiyama.data.repositories.LatestAnimeRepository
import com.fmaldonado.akiyama.ui.common.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject
constructor(
    private val latestAnimeRepository: LatestAnimeRepository
) : ViewModel() {

    private val latestAnimeData = MutableLiveData<Triple
    <LatestAnimeSections, List<Anime>?, List<Episode>?>>()

    private val latestAnimeStatus = MutableLiveData<Pair<LatestAnimeSections, Status>>()

    private val animeRepositoryFunctions = listOf<suspend (Boolean) -> List<Anime>>(
        latestAnimeRepository::getLatestAnimes,
        latestAnimeRepository::getLatestMovies,
        latestAnimeRepository::getLatestOvas,
        latestAnimeRepository::getLatestSpecials
    )

    fun getLatestAnimeData() = latestAnimeData as LiveData<Triple
    <LatestAnimeSections, List<Anime>?, List<Episode>?>>

    fun getLatestAnimeStatus() = latestAnimeStatus as LiveData<Pair<LatestAnimeSections, Status>>

    fun getAnimeSection(shouldFetch: Boolean, section: LatestAnimeSections) {
        viewModelScope.launch(Dispatchers.IO) {
            latestAnimeStatus.postValue(Pair(section, Status.Loading))

            val sectionIndex =
                if (section.ordinal - 1 >= animeRepositoryFunctions.size) 0
                else section.ordinal - 1

            try {

                val animes = animeRepositoryFunctions[sectionIndex](shouldFetch)

                latestAnimeData.postValue(Triple(section, animes, null))
                latestAnimeStatus.postValue(Pair(section, Status.Loaded))

            } catch (e: Exception) {

            }

        }
    }

    fun getLatestEpisodes(shouldFetch: Boolean) {
        val section = LatestAnimeSections.LatestEpisodes
        viewModelScope.launch(Dispatchers.IO) {
            latestAnimeStatus.postValue(Pair(section, Status.Loading))
            try {
                val episodes = latestAnimeRepository.getLatestEpisodes(shouldFetch)
                latestAnimeData.postValue(Triple(section, null, episodes))
                latestAnimeStatus.postValue(Pair(section, Status.Loaded))
            } catch (e: Exception) {

            }

        }
    }

}