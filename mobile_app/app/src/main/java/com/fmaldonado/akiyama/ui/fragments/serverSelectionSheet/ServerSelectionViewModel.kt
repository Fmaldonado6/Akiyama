package com.fmaldonado.akiyama.ui.fragments.serverSelectionSheet

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fmaldonado.akiyama.data.models.content.Episode
import com.fmaldonado.akiyama.data.models.content.Server
import com.fmaldonado.akiyama.data.repositories.EpisodesRepository
import com.fmaldonado.akiyama.ui.common.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ServerSelectionViewModel
@Inject
constructor(
    private val episodesRepository: EpisodesRepository
) : ViewModel() {

    private val status = MutableLiveData(Status.Loading)

    private val servers = MutableLiveData<List<Server>>()

    fun getStatus() = status as LiveData<Status>

    fun getServerData() = servers as LiveData<List<Server>>

    fun getServers(episode: Episode) {
        viewModelScope.launch(Dispatchers.IO) {
            status.postValue(Status.Loading)
            try {
                val response = episodesRepository.getEpisodeServers(episode)
                servers.postValue(response)
                status.postValue(Status.Loaded)
            } catch (e: Exception) {
                Log.e("E", "error", e)
            }
        }
    }

}