package com.fmaldonado.akiyama.ui.activities.main.fragments.favorites

import androidx.lifecycle.*
import com.fmaldonado.akiyama.data.models.content.Anime
import com.fmaldonado.akiyama.data.repositories.favorites.FavoritesRepository
import com.fmaldonado.akiyama.ui.common.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel
@Inject
constructor(
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {

    val currentStatus = MutableLiveData(Status.Loading)
    val favorites = favoritesRepository.favorites

}