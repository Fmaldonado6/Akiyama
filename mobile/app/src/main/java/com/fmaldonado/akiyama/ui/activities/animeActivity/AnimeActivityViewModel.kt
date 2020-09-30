package com.fmaldonado.akiyama.ui.activities.animeActivity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fmaldonado.akiyama.data.models.apiContent.Status
import com.fmaldonado.akiyama.data.repositories.AruppiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class AnimeActivityViewModel(
    private val aruppiRepository: AruppiRepository
) : ViewModel() {
    val currentStatus = MutableLiveData<Int>()

    val animeInfo = aruppiRepository.animeInfo

    fun getAnimeInfo(animeTitle: String) {
        currentStatus.postValue(Status.Loading.value)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                aruppiRepository.getAnimeInfo(animeTitle)
                currentStatus.postValue(Status.Loaded.value)

            } catch (e: Exception) {
                Log.d("Error",e.message)
                currentStatus.postValue(Status.Error.value)
            }
        }

    }
}