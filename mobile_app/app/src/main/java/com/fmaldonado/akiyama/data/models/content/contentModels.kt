package com.fmaldonado.akiyama.data.models.content

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Server(
    val server: String = "",
    val title: String = "",
    val allowMobile: Boolean = false,
    val url: String = ""
) : Parcelable

@Parcelize
data class Episode(
    val id: String = "",
    val title: String = "",
    val poster: String = "",
    val episode: Float = 0f,
    val servers: List<Server> = mutableListOf(),
    val nextEpisodeDate: String? = null
) : Parcelable

@Parcelize
data class Anime(
    val id: String = "",
    val title: String = "",
    val type: String = "",
    val poster: String = "",
    val synopsis: String = "",
    val debut: String = "",
    val rating: String = "",
    val genres: List<String> = mutableListOf(),
    val episodes: List<Episode> = mutableListOf()
) : Parcelable
