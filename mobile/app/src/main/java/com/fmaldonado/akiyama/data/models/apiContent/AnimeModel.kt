package com.fmaldonado.akiyama.data.models.apiContent

data class AnimeModel(
    val id: String,
    val title: String,
    val type: String,
    val image: String,
    val poster: String,
    val synopsis: String,
    val status: String,
    val genres: List<String>,
    val episodes: List<EpisodeModel>
) {
}