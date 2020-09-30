package com.fmaldonado.akiyama.ui.fragments.animeDisplay

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fmaldonado.akiyama.data.repositories.AruppiRepository

class AnimeDisplayViewModelFactory(
    private val aruppiRepository: AruppiRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AnimeDisplayViewModel(aruppiRepository) as T
    }
}