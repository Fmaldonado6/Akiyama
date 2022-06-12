package com.fmaldonado.akiyama.ui.activities.main.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fmaldonado.akiyama.data.models.content.Anime
import com.fmaldonado.akiyama.data.models.content.Episode
import com.fmaldonado.akiyama.data.models.content.MainScreenContent
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

    private val latestAnimeData =
        MutableLiveData<Pair<LatestAnimeSections, List<MainScreenContent>>>()

    private val latestAnimeStatus = MutableLiveData<Pair<LatestAnimeSections, Status>>()

    private val animeRepositoryFunctions = listOf<suspend (Boolean) -> List<MainScreenContent>>(
        latestAnimeRepository::getLatestEpisodes,
        latestAnimeRepository::getLatestAnimes,
        latestAnimeRepository::getLatestMovies,
        latestAnimeRepository::getLatestOvas,
        latestAnimeRepository::getLatestSpecials
    )

    fun getLatestAnimeData() =
        latestAnimeData as LiveData<Pair<LatestAnimeSections, List<MainScreenContent>>>

    fun getLatestAnimeStatus() = latestAnimeStatus as LiveData<Pair<LatestAnimeSections, Status>>

    fun getAnimeSection(shouldFetch: Boolean, section: LatestAnimeSections) {
        viewModelScope.launch(Dispatchers.IO) {
            latestAnimeStatus.postValue(Pair(section, Status.Loading))

            try {
                val animes = animeRepositoryFunctions[section.ordinal](shouldFetch)
                latestAnimeData.postValue(Pair(section, animes))
                latestAnimeStatus.postValue(Pair(section, Status.Loaded))

            } catch (e: Exception) {

            }

        }
    }


}