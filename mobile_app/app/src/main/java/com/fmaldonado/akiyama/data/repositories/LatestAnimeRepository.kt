package com.fmaldonado.akiyama.data.repositories

import com.fmaldonado.akiyama.data.models.content.Anime
import com.fmaldonado.akiyama.data.models.content.Episode
import com.fmaldonado.akiyama.data.network.NetworkDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LatestAnimeRepository
@Inject
constructor(
    private val networkDataSource: NetworkDataSource
) {

    private var latestAnime: List<Anime>? = null
    private var latestSpecials: List<Anime>? = null
    private var latestOvas: List<Anime>? = null
    private var latestMovies: List<Anime>? = null
    private var latestEpisodes: List<Episode>? = null


    suspend fun getLatestAnimes(
        shouldFetch: Boolean = false
    ): List<Anime> {

        if (shouldFetch || latestAnime == null)
            latestAnime = networkDataSource.getAnimes()

        return latestAnime ?: mutableListOf()
    }

    suspend fun getLatestSpecials(
        shouldFetch: Boolean = false
    ): List<Anime> {

        if (shouldFetch || latestSpecials == null)
            latestSpecials = networkDataSource.getSpecials()

        return latestSpecials ?: mutableListOf()
    }

    suspend fun getLatestOvas(
        shouldFetch: Boolean = false
    ): List<Anime> {

        if (shouldFetch || latestOvas == null)
            latestOvas = networkDataSource.getOvas()

        return latestOvas ?: mutableListOf()
    }

    suspend fun getLatestMovies(
        shouldFetch: Boolean = false
    ): List<Anime> {

        if (shouldFetch || latestMovies == null)
            latestMovies = networkDataSource.getMovies()

        return latestMovies ?: mutableListOf()
    }

    suspend fun getLatestEpisodes(
        shouldFetch: Boolean = false
    ): List<Episode> {

        if (shouldFetch || latestEpisodes == null)
            latestEpisodes = networkDataSource.getEpisodes()

        return latestEpisodes ?: mutableListOf()
    }

}