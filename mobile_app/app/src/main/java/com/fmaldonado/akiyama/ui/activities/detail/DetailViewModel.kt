package com.fmaldonado.akiyama.ui.activities.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fmaldonado.akiyama.data.models.content.Anime
import com.fmaldonado.akiyama.data.models.content.Episode
import com.fmaldonado.akiyama.data.models.content.MainScreenContent
import com.fmaldonado.akiyama.data.repositories.AnimeRepository
import com.fmaldonado.akiyama.data.repositories.FavoritesRepository
import com.fmaldonado.akiyama.ui.common.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class DetailViewModel
@Inject
constructor(
    private val animeRepository: AnimeRepository,
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {

    private val status = MutableLiveData(Status.Loading)
    private val animeInfo = MutableLiveData<Anime>()
    private val isFavorite = MutableLiveData(false)

    fun getStatus() = status as LiveData<Status>

    fun getAnimeInfoData() = animeInfo as LiveData<Anime>

    fun getIsFavorite() = isFavorite as LiveData<Boolean>

    fun getAnimeInfo(anime: Anime) {
        viewModelScope.launch(Dispatchers.IO) {
            status.postValue(Status.Loading)
            try {
                val result = animeRepository.getAnimeInfo(anime)
                checkIfFavorite(anime)
                animeInfo.postValue(result)
                status.postValue(Status.Loaded)
            } catch (e: Exception) {
            }
        }
    }

    private fun checkIfFavorite(anime: Anime) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = favoritesRepository.isFavorite(anime.id)
            isFavorite.postValue(result)
        }
    }


    fun addToFavorites(anime: Anime) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = favoritesRepository.isFavorite(anime.id)

            if (!result) favoritesRepository.addFavorite(anime)
            else favoritesRepository.removeFavorite(anime)

            checkIfFavorite(anime)

        }
    }

}