package com.fmaldonado.akiyama.ui.fragments.animeDisplay

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fmaldonado.akiyama.data.models.apiContent.Status
import com.fmaldonado.akiyama.data.repositories.AruppiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class AnimeDisplayViewModel(
    private val aruppiRepository: AruppiRepository
) : ViewModel() {

    val status = MutableLiveData<Int>()

    val episodes = aruppiRepository.latestEpisodes

    val animes = aruppiRepository.latestAnimes

    val specials = aruppiRepository.latestSpecials

    val ovas = aruppiRepository.latestOvas

    val movies = aruppiRepository.latestMovies


    fun getData(type: Int) {
        status.postValue(Status.Loading.value)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                aruppiRepository.getData(type)
                status.postValue(Status.Loaded.value)
            } catch (e: Exception) {
                Log.d("Error", e.message)
                status.postValue(Status.Error.value)

            }
        }
    }

}