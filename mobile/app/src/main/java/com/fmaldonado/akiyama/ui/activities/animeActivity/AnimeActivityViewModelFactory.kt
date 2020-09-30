package com.fmaldonado.akiyama.ui.activities.animeActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fmaldonado.akiyama.data.repositories.AruppiRepository

@Suppress("UNCHECKED_CAST")
class AnimeActivityViewModelFactory(
    private val aruppiRepository: AruppiRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AnimeActivityViewModel(aruppiRepository) as T
    }
}