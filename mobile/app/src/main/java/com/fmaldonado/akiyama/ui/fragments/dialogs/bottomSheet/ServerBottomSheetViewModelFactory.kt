package com.fmaldonado.akiyama.ui.fragments.dialogs.bottomSheet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fmaldonado.akiyama.data.repositories.AruppiRepository
import com.fmaldonado.akiyama.ui.fragments.animeDisplay.AnimeDisplayViewModel

class ServerBottomSheetViewModelFactory(
    private val aruppiRepository: AruppiRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ServerBottomSheetViewModel(aruppiRepository) as T
    }
}