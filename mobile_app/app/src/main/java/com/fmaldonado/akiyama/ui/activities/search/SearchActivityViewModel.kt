package com.fmaldonado.akiyama.ui.activities.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fmaldonado.akiyama.data.models.content.Anime
import com.fmaldonado.akiyama.data.repositories.anime.AnimeRepository
import com.fmaldonado.akiyama.ui.common.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SearchActivityViewModel
@Inject
constructor(
    private val animeRepository: AnimeRepository
) : ViewModel() {
    var isCurrentlySearching = false
    val currentStatus = MutableLiveData(Status.Loading)
    val searchResults = animeRepository.currentSearch
    val currentSearchQuery = animeRepository.currentSearchQuery
    val currentSearch = animeRepository.currentSearch

    fun makeSearch(search: String) {
        viewModelScope.launch(Dispatchers.IO) {
            currentStatus.postValue(Status.Loading)
            isCurrentlySearching = true
            try {
                animeRepository.makeSearch(search)
                currentStatus.postValue(Status.Loaded)
                isCurrentlySearching = false
            } catch (e: Exception) {
                animeRepository.currentSearch.postValue(mutableListOf())
                currentStatus.postValue(Status.Empty)
            }
        }
    }

}