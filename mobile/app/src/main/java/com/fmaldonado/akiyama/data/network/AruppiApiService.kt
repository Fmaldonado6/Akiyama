package com.fmaldonado.akiyama.data.network

import com.fmaldonado.akiyama.BuildConfig
import com.fmaldonado.akiyama.data.models.responses.*
import com.fmaldonado.akiyama.data.network.interceptor.NetworkInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface AruppiApiService {
    @GET("lastEpisodes")
    fun getEpisodes(): Deferred<EpisodeResponse>

    @GET("tv/latest/1")
    fun getAnimes(): Deferred<AnimeResponse>

    @GET("movies/latest/1")
    fun getMovies(): Deferred<MoviesResponse>

    @GET("specials/latest/1")
    fun getSpecials(): Deferred<SpecialsResponse>

    @GET("ovas/latest/1")
    fun getOvas(): Deferred<OvasResponse>

    @GET("search/{title}")
    fun getSearch(@Path("title") title: String): Deferred<SearchResponse>

    @GET("moreInfo/{title}")
    fun getAnimeInfo(@Path("title") title: String): Deferred<AnimeInfoResponse>

    @GET("getAnimeServers/{id}/{animeName}")
    fun getServers(
        @Path("id") id: String,
        @Path("animeName") animeName: String
    ): Deferred<ServerResponse>

    companion object {
        operator fun invoke(networkInterceptor: NetworkInterceptor): AruppiApiService {


            val specs = listOf(ConnectionSpec.CLEARTEXT, ConnectionSpec.MODERN_TLS)
            val okHttpClient =
                OkHttpClient.Builder()
                    .connectionSpecs(specs)
                    .addInterceptor(networkInterceptor)
                    .build()

            return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(okHttpClient)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create()).build()
                .create(AruppiApiService::class.java)
        }
    }

}