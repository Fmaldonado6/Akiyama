package com.fmaldonado.akiyama.data.models.content

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Server(
    val id: String = "",
    val url: String = ""
) : Parcelable

@Parcelize
data class Episode(
    val id: String = "",
    val title: String = "",
    val image: String = "",
    val episode: Float = 0f,
    val servers: List<Server> = mutableListOf()
) : Parcelable

@Parcelize
data class Anime(
    val id: String = "",
    val title: String = "",
    val type: String = "",
    val image: String = "",
    val synopsis: String = "",
    val status: String = "",
    val genres: List<String> = mutableListOf(),
    val episodes: List<Episode> = mutableListOf()
) : Parcelable
