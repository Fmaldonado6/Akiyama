package com.fmaldonado.akiyama.ui.activities.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fmaldonado.akiyama.data.repositories.AnimeRepository
import com.fmaldonado.akiyama.data.repositories.UpdaterRepository
import com.fmaldonado.akiyama.ui.common.UpdateEvents
import com.fmaldonado.akiyama.ui.common.UpdateStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel
@Inject
constructor(
    private val updaterRepository: UpdaterRepository
) : ViewModel() {

    val hasNewVersion = updaterRepository.hasNewVersion
    fun checkLatestVersion(manual: Boolean = false) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                updaterRepository.lookForNewVersion(manual)
            } catch (e: Exception) {
                updaterRepository.hasNewVersion.postValue(UpdateEvents(UpdateStatus.Error, manual))
            }
        }
    }
}