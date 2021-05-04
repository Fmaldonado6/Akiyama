package com.fmaldonado.akiyama.ui.activities.animeDetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fmaldonado.akiyama.data.models.content.Anime
import com.fmaldonado.akiyama.data.repositories.favorites.FavoritesRepository
import com.fmaldonado.akiyama.ui.common.FavoritesStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class AnimeDetailViewModel
@Inject
constructor(
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {

    val showToast = MutableLiveData<FavoritesStatus>()
    val isFavorite = MutableLiveData<Boolean>(false)

    fun getIsFavorite(animeId: String) {
        viewModelScope.launch {
            try {
                isFavorite.postValue(favoritesRepository.isFavorite(animeId))
            } catch (e: Exception) {
                Log.d("AnimeDetailVieModel", "Error get favorite", e)
            }
        }
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
                Log.e("E","error",e)
                showToast.postValue(FavoritesStatus.Error)

            }
        }
    }


}