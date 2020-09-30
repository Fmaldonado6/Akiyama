package com.fmaldonado.akiyama.data.models.responses

import com.fmaldonado.akiyama.data.models.apiContent.EpisodeModel

data class EpisodeResponse(
    val episodes: List<EpisodeModel>
) {
}