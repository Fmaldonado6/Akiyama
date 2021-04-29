package com.fmaldonado.akiyama.data.models.apiResponses

import com.fmaldonado.akiyama.data.models.content.Anime
import com.fmaldonado.akiyama.data.models.content.Episode
import com.fmaldonado.akiyama.data.models.content.Server

data class AnimeInfoResponse(
    val info: List<Anime>
)

data class AnimeResponse(
    val animes: List<Anime>
)

data class EpisodeResponse(
    val episodes: List<Episode>
)

data class ServerResponse(
    val servers: List<Server>
)