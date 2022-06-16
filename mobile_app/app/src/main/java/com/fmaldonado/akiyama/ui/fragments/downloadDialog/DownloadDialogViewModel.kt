package com.fmaldonado.akiyama.ui.fragments.downloadDialog

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fmaldonado.akiyama.data.repositories.UpdaterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DownloadDialogViewModel
@Inject
constructor(
    private val updaterRepository: UpdaterRepository
) : ViewModel() {

    val file = updaterRepository.newVersionUri
    fun downloadLatest() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                updaterRepository.downloadLatest()
            } catch (e: Exception) {
                Log.e("Error", "e", e)
            }
        }
    }

}