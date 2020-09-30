package com.fmaldonado.akiyama.ui.fragments.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fmaldonado.akiyama.data.repositories.AruppiRepository


@Suppress("UNCHECKED_CAST")
class HomeFragmentViewModelFactory(
    private val aruppiRepository: AruppiRepository
) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(
            aruppiRepository
        ) as T
    }
}