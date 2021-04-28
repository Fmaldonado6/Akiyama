package com.fmaldonado.akiyama.data.models.content

data class Server(
    val id: String,
    val url: String
)

data class Episode(
    val id:String,
    val title:String,
    val image:String,
    val episode:Float,
    val servers:List<Server>
)

data class Anime(
    val id: String,
    val title: String,
    val type: String,
    val image: String,
    val poster: String,
    val synopsis: String,
    val status: String,
    val genres: List<String>,
    val episodes: List<Episode>
)
