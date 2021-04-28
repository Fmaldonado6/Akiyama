package com.fmaldonado.akiyama.data.network

import com.fmaldonado.akiyama.data.models.apiResponses.*
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AruppiDataSource {
    @GET("lastEpisodes")
    suspend fun getEpisodes(): EpisodeResponse

    @GET("tv/latest/1")
    suspend fun getAnimes(): AnimeResponse

    @GET("movies/latest/1")
    suspend fun getMovies(): MoviesResponse

    @GET("specials/latest/1")
    suspend fun getSpecials(): SpecialsResponse

    @GET("ovas/latest/1")
    suspend fun getOvas(): OvasResponse

    @GET("search/{title}")
    suspend fun getSearch(@Path("title") title: String): SearchResponse

    @GET("moreInfo/{title}")
    suspend fun getAnimeInfo(@Path("title") title: String): AnimeInfoResponse

    @GET("getAnimeServers/{id}/{animeName}")
    suspend fun getServers(
        @Path("id") id: String,
        @Path("animeName") animeName: String
    ): ServerResponse
}