package com.fmaldonado.akiyama.ui.activities.main.fragments.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fmaldonado.akiyama.data.repositories.anime.AnimeRepository
import com.fmaldonado.akiyama.ui.common.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class HomeViewModel
@Inject
constructor(
    private val animeRepository: AnimeRepository
) : ViewModel() {
    val latestEpisodesStatus = MutableLiveData(Status.Loading)
    val latestAnimesStatus = MutableLiveData(Status.Loading)
    val latestMoviesStatus = MutableLiveData(Status.Loading)
    val latestSpecialsStatus = MutableLiveData(Status.Loading)
    val latestOvasStatus = MutableLiveData(Status.Loading)


    val latestEpisodes = animeRepository.latestEpisodes
    val latestAnimes = animeRepository.latestAnimes
    val latestMovies = animeRepository.latestMovies
    val latestOvas = animeRepository.latestOvas
    val latestSpecials = animeRepository.latestSpecials

    fun getLatestEpisodes() {

        if (latestEpisodes.value != null) {
            latestEpisodesStatus.postValue(Status.Loaded)
            return
        }

        latestEpisodesStatus.postValue(Status.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                animeRepository.getLatestEpisodes()
                latestEpisodesStatus.postValue(Status.Loaded)
            } catch (e: Exception) {
                Log.e("Error", "Error", e)
                latestEpisodesStatus.postValue(Status.Error)
            }
        }
    }

    fun getLatestAnimes() {
        if (latestAnimes.value != null) {
            latestAnimesStatus.postValue(Status.Loaded)
            return
        }

        latestAnimesStatus.postValue(Status.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                animeRepository.getLatestAnimes()
                latestAnimesStatus.postValue(Status.Loaded)
            } catch (e: Exception) {
                Log.e("Error", "Error", e)
                latestAnimesStatus.postValue(Status.Error)
            }
        }
    }

    fun getLatestMovies() {
        if (latestMovies.value != null) {
            latestMoviesStatus.postValue(Status.Loaded)
            return
        }

        latestMoviesStatus.postValue(Status.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                animeRepository.getLatestMovies()
                latestMoviesStatus.postValue(Status.Loaded)
            } catch (e: Exception) {
                Log.e("Error", "Error", e)
                latestMoviesStatus.postValue(Status.Error)

            }
        }
    }

    fun getLatestOvas() {
        if (latestOvas.value != null){
            latestOvasStatus.postValue(Status.Loaded)
            return
        }

        latestOvasStatus.postValue(Status.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                animeRepository.getLatestOvas()
                latestOvasStatus.postValue(Status.Loaded)
            } catch (e: Exception) {
                Log.e("Error", "Error", e)
                latestOvasStatus.postValue(Status.Error)
            }
        }
    }

    fun getLatestSpecials() {
        if (latestSpecials.value != null){
            latestSpecialsStatus.postValue(Status.Loaded)
            return
        }
        latestSpecialsStatus.postValue(Status.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                animeRepository.getLatestSpecials()
                latestSpecialsStatus.postValue(Status.Loaded)
            } catch (e: Exception) {
                Log.e("Error", "Error", e)
                latestSpecialsStatus.postValue(Status.Error)
            }

        }
    }

}