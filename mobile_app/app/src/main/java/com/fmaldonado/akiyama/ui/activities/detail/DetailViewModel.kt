package com.fmaldonado.akiyama.ui.activities.detail

import androidx.lifecycle.ViewModel
import com.fmaldonado.akiyama.data.repositories.AnimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel
@Inject
constructor(
    private val animeRepository: AnimeRepository
) : ViewModel(){
}