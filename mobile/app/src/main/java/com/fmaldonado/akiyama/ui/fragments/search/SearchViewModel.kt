package com.fmaldonado.akiyama.ui.fragments.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fmaldonado.akiyama.data.models.apiContent.Status
import com.fmaldonado.akiyama.data.network.exceptions.AppError
import com.fmaldonado.akiyama.data.network.exceptions.EmptySearch
import com.fmaldonado.akiyama.data.network.exceptions.NotFoundError
import com.fmaldonado.akiyama.data.repositories.AruppiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class SearchViewModel(
    private val aruppiRepository: AruppiRepository
) : ViewModel() {

    val status = MutableLiveData<Int>()

    val searchResults = aruppiRepository.searchResults

    fun makeSearch(query: String) {
        status.postValue(Status.Loading.value)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                aruppiRepository.getSearch(query)
                status.postValue(Status.Loaded.value)
            } catch (e: Exception) {

                if (e is EmptySearch || e is NotFoundError)
                    status.postValue(Status.Empty.value)
                else
                    status.postValue(Status.Error.value)
            }
        }

    }

}