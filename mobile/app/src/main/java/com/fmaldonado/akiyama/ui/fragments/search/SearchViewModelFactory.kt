package com.fmaldonado.akiyama.ui.fragments.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fmaldonado.akiyama.data.repositories.AruppiRepository
import com.fmaldonado.akiyama.ui.fragments.search.SearchViewModel

@Suppress("UNCHECKED_CAST")
class SearchViewModelFactory(private val aruppiRepository: AruppiRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchViewModel(aruppiRepository) as T
    }
}