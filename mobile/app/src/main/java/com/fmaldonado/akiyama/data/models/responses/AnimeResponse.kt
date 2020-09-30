package com.fmaldonado.akiyama.data.models.responses

import com.fmaldonado.akiyama.data.models.apiContent.AnimeModel

data class AnimeResponse(
    val tv: List<AnimeModel>
) {
}