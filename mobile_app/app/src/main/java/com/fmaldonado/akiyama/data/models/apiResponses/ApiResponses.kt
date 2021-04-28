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

data class MoviesResponse(
    val movies: List<Anime>
)

data class OvasResponse(
    val ovas: List<Anime>
)

data class SearchResponse(
    val search: List<Anime>
)

data class SpecialsResponse(
    val specials: List<Anime>
)
data class ServerResponse(
    val servers: List<Server>
)