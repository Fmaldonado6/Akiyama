package com.fmaldonado.akiyama.ui.common.fragments.serverBottomSheet

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fmaldonado.akiyama.data.models.content.Server
import com.fmaldonado.akiyama.data.repositories.anime.AnimeRepository
import com.fmaldonado.akiyama.ui.common.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ServerBottomSheetViewModel
@Inject
constructor(
    private val animeRepository: AnimeRepository
) : ViewModel() {

    val currentStatus = MutableLiveData(Status.Loading)
    val servers = MutableLiveData<List<Server>>()
    fun getEpisodeServers(episodeId: String) {

        if (servers.value != null)
            return

        viewModelScope.launch(Dispatchers.IO) {
            currentStatus.postValue(Status.Loading)
            try {
                val episodeServers = animeRepository.getEpisodeServers(episodeId)
                servers.postValue(episodeServers)
                currentStatus.postValue(Status.Loaded)
            } catch (e: Exception) {
                Log.d("Server error", "error", e)
                currentStatus.postValue(Status.Error)

            }
        }
    }

    fun setServers(servers: List<Server>) {
        this.servers.value = servers
        this.currentStatus.value = Status.Loaded
    }

}