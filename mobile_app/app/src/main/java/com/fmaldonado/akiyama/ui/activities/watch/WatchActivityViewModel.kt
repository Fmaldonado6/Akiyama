package com.fmaldonado.akiyama.ui.activities.watch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fmaldonado.akiyama.data.models.content.Server
import com.fmaldonado.akiyama.data.models.video.VideoResponse
import com.fmaldonado.akiyama.data.repositories.VideoRepository
import com.fmaldonado.akiyama.ui.common.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WatchActivityViewModel
@Inject
constructor(
    private val videoRepository: VideoRepository
) : ViewModel() {
    private val videoStatus = MutableLiveData(Status.Loading)

    private val urlValue = MutableLiveData<VideoResponse>()

    fun getStatus() = videoStatus as LiveData<Status>

    fun getUrlValue() = urlValue as LiveData<VideoResponse>

    fun getVideoUrl(server: Server) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                videoStatus.postValue(Status.Loading)
                val video = videoRepository.getVideoUrl(server)
                urlValue.postValue(video)
                videoStatus.postValue(Status.Loaded)
            } catch (e: Exception) {
                videoStatus.postValue(Status.Error)
            }
        }
    }

    fun setStatus(status: Status) {
        videoStatus.postValue(status)
    }
}