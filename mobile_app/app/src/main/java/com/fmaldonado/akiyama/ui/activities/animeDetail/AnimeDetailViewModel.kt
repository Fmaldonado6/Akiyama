package com.fmaldonado.akiyama.ui.activities.animeDetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fmaldonado.akiyama.data.models.content.Anime
import com.fmaldonado.akiyama.data.models.content.Episode
import com.fmaldonado.akiyama.data.repositories.anime.AnimeRepository
import com.fmaldonado.akiyama.data.repositories.favorites.FavoritesRepository
import com.fmaldonado.akiyama.ui.common.FavoritesStatus
import com.fmaldonado.akiyama.ui.common.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class AnimeDetailViewModel
@Inject
constructor(
    private val favoritesRepository: FavoritesRepository,
    private val animeRepository: AnimeRepository
) : ViewModel() {

    val showToast = MutableLiveData<FavoritesStatus>()

    var gettingFavorite = true
    val isFavorite = MutableLiveData<Boolean>(false)

    val episodes = MutableLiveData<List<Episode>>()
    val episodesStatus = MutableLiveData(Status.Loading)

    fun getIsFavorite(animeId: String) {
        viewModelScope.launch {
            try {
                gettingFavorite = true
                isFavorite.postValue(favoritesRepository.isFavorite(animeId))
                gettingFavorite = false
            } catch (e: Exception) {
                Log.d("AnimeDetailVieModel", "Error get favorite", e)
            }
        }
    }

    fun getAnimeEpisodes(anime: Anime) {
        episodesStatus.postValue(Status.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val info = animeRepository.getAnimeInfo(anime.id, anime.title)
                setAnimeEpisodes(info.episodes)
            } catch (e: Exception) {
                Log.e("Error", "Error", e)
                setAnimeEpisodes(anime.episodes)
            }
        }
    }

    fun setAnimeEpisodes(episodes: List<Episode>) {
        if (gettingFavorite)
            return

        this.episodes.postValue(episodes)
        episodesStatus.postValue(Status.Loaded)
    }

    fun addFavorite(anime: Anime) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isFavorite.value?.let {
                    if (!it) {
                        favoritesRepository.addFavorite(anime)
                        showToast.postValue(FavoritesStatus.Added)
                        isFavorite.postValue(true)
                    } else {
                        favoritesRepository.removeFavorite(anime)
                        showToast.postValue(FavoritesStatus.Removed)
                        isFavorite.postValue(false)
                    }

                }
            } catch (e: Exception) {
                Log.e("E", "error", e)
                showToast.postValue(FavoritesStatus.Error)

            }
        }
    }


}