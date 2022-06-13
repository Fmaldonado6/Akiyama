package com.fmaldonado.akiyama.ui.activities.main.fragments.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fmaldonado.akiyama.data.repositories.FavoritesRepository
import com.fmaldonado.akiyama.ui.common.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel
@Inject
constructor(
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {

    val favorites = favoritesRepository.favoritesList

}