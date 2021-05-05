package com.fmaldonado.akiyama.ui.activities.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fmaldonado.akiyama.data.repositories.updater.UpdaterRepository
import com.fmaldonado.akiyama.ui.common.UpdateEvents
import com.fmaldonado.akiyama.ui.common.UpdateStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
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