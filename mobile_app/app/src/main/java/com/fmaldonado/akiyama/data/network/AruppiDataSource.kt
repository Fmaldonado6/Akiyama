package com.fmaldonado.akiyama.data.network

import com.fmaldonado.akiyama.data.models.apiResponses.*
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AruppiDataSource {
    @GET("lastEpisodes")
    fun getEpisodes(): Response<EpisodeResponse>

    @GET("tv/latest/1")
    fun getAnimes(): Response<AnimeResponse>

    @GET("movies/latest/1")
    fun getMovies(): Response<MoviesResponse>

    @GET("specials/latest/1")
    fun getSpecials(): Response<SpecialsResponse>

    @GET("ovas/latest/1")
    fun getOvas(): Response<OvasResponse>

    @GET("search/{title}")
    fun getSearch(@Path("title") title: String): Response<SearchResponse>

    @GET("moreInfo/{title}")
    fun getAnimeInfo(@Path("title") title: String): Response<AnimeInfoResponse>

    @GET("getAnimeServers/{id}/{animeName}")
    fun getServers(
        @Path("id") id: String,
        @Path("animeName") animeName: String
    ): Response<ServerResponse>
}