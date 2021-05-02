package com.fmaldonado.akiyama.data.network

import com.fmaldonado.akiyama.data.models.apiResponses.*
import com.fmaldonado.akiyama.data.models.content.Anime
import com.fmaldonado.akiyama.data.models.content.Episode
import com.fmaldonado.akiyama.data.models.content.Server
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AruppiDataSource {
    @GET("episodes")
    suspend fun getEpisodes(): List<Episode>

    @GET("latest")
    suspend fun getAnimes(): List<Anime>

    @GET("movies")
    suspend fun getMovies(): List<Anime>

    @GET("specials")
    suspend fun getSpecials(): List<Anime>

    @GET("ovas")
    suspend fun getOvas(): List<Anime>

    @GET("search/{title}")
    suspend fun getSearch(@Path("title") title: String): List<Anime>

    @GET("animeInfo/{animeId}/{animeTitle}")
    suspend fun getAnimeInfo(
        @Path("animeId") animeId: String,
        @Path("animeTitle") title: String
    ): Anime

    @GET("servers/{id}")
    suspend fun getServers(
        @Path("id") id: String,
    ): List<Server>
}