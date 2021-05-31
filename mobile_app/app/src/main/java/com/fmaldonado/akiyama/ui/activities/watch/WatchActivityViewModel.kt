package com.fmaldonado.akiyama.ui.activities.watch

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fmaldonado.akiyama.data.models.content.Server
import com.fmaldonado.akiyama.data.models.video.VideoResponse
import com.fmaldonado.akiyama.data.repositories.videos.VideoRepository
import com.fmaldonado.akiyama.ui.common.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class WatchActivityViewModel
@Inject
constructor(
    private val videoRepository: VideoRepository
) : ViewModel() {

    val videoStatus = MutableLiveData(Status.Loading)

    val urlValue = MutableLiveData<VideoResponse>()

    fun getVideoUrl(server: Server) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                videoStatus.postValue(Status.Loading)
                val video = videoRepository.getVideoUrl(server)
                urlValue.postValue(video)
                videoStatus.postValue(Status.Loaded)
            } catch (e: Exception) {
                Log.e("E", "e", e)
                videoStatus.postValue(Status.Error)
            }
        }
    }

    fun setStatus(status: Status){
        videoStatus.postValue(status)
    }

}