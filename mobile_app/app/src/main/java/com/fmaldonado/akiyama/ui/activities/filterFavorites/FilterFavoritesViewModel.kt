package com.fmaldonado.akiyama.ui.activities.filterFavorites

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fmaldonado.akiyama.data.repositories.FavoritesRepository
import com.fmaldonado.akiyama.ui.common.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FilterFavoritesViewModel
@Inject
constructor(
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {

    private val favorites = favoritesRepository.favoritesList.value ?: mutableListOf()

    private var filteredFavorites = favorites

    private val status = MutableLiveData(Status.Loaded)

    fun getStatus() = status as LiveData<Status>

    fun getFavorites() = filteredFavorites

    fun filterFavorites(query: String) {
        val filteredList = favorites.filter { it.title.lowercase().contains(query.lowercase()) }
        Log.d("Size", filteredList.size.toString())
        filteredFavorites = filteredList
        if (filteredList.isEmpty())
            status.value = Status.Empty
        else
            status.value = Status.Loaded
    }


}