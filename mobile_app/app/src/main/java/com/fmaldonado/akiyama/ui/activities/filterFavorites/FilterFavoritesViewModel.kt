package com.fmaldonado.akiyama.ui.activities.filterFavorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fmaldonado.akiyama.data.repositories.favorites.FavoritesRepository
import com.fmaldonado.akiyama.ui.common.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FilterFavoritesViewModel
@Inject
constructor(
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {
    val currentStatus = MutableLiveData(Status.Loading)
    val favorites = favoritesRepository.favorites
}