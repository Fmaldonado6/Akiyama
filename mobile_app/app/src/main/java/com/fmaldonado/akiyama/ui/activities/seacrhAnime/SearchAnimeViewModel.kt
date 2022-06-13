package com.fmaldonado.akiyama.ui.activities.seacrhAnime

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fmaldonado.akiyama.data.models.content.Anime
import com.fmaldonado.akiyama.data.repositories.AnimeRepository
import com.fmaldonado.akiyama.ui.common.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SearchAnimeViewModel
@Inject
constructor(
    private val animeRepository: AnimeRepository
) : ViewModel() {

    private val searchResults = MutableLiveData<List<Anime>>()
    private val status = MutableLiveData(Status.Loading)

    fun getSearchResults() = searchResults as LiveData<List<Anime>>
    fun getStatus() = status as LiveData<Status>

    fun getInitialSearch() {
        viewModelScope.launch(Dispatchers.IO) {
            status.postValue(Status.Loading)
            try {
                val result = animeRepository.getCurrentSearch()
                searchResults.postValue(result)
                status.postValue(Status.Loaded)
            } catch (e: Exception) {

            }
        }
    }

    fun makeSearch(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            status.postValue(Status.Loading)
            try {
                val result = animeRepository.makeSearch(query)
                searchResults.postValue(result)
                status.postValue(Status.Loaded)
            } catch (e: Exception) {
            }
        }
    }

}