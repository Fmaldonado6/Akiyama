package com.fmaldonado.akiyama.ui.fragments.dialogs.bottomSheet

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fmaldonado.akiyama.data.models.apiContent.Status
import com.fmaldonado.akiyama.data.repositories.AruppiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class ServerBottomSheetViewModel(private val aruppiRepository: AruppiRepository) : ViewModel() {

    val status = MutableLiveData<Int>()

    val servers = aruppiRepository.servers

    fun getServers(id: String) {
        status.postValue(Status.Loading.value)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                aruppiRepository.getServers(id)
                status.postValue(Status.Loaded.value)
            } catch (e: Exception) {
                status.postValue(Status.Error.value)
            }
        }
    }
}