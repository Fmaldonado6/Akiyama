package com.fmaldonado.akiyama.data.network

import com.fmaldonado.akiyama.data.models.version.GithubVersion
import retrofit2.http.GET

interface LatestVersionDataSource {
    @GET("latest")
    suspend fun getLatestRelease(): GithubVersion
}