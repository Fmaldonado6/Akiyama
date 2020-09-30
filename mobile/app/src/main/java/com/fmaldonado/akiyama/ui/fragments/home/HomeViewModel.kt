package com.fmaldonado.akiyama.ui.fragments.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fmaldonado.akiyama.data.models.apiContent.Status
import com.fmaldonado.akiyama.data.repositories.AruppiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception


class HomeViewModel(private val aruppiRepository: AruppiRepository) : ViewModel() {

    val episodesStatus = MutableLiveData<Int>()
    val animeStatus = MutableLiveData<Int>()
    val specialsStatus = MutableLiveData<Int>()
    val ovasStatus = MutableLiveData<Int>()
    val moviesStatus = MutableLiveData<Int>()

    val episodes = aruppiRepository.latestEpisodes

    val animes = aruppiRepository.latestAnimes

    val specials = aruppiRepository.latestSpecials

    val ovas = aruppiRepository.latestOvas

    val movies = aruppiRepository.latestMovies



}