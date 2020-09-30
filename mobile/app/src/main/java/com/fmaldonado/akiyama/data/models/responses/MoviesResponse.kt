package com.fmaldonado.akiyama.data.models.responses

import com.fmaldonado.akiyama.data.models.apiContent.AnimeModel

data class MoviesResponse(
    val movies: List<AnimeModel>
) {

}