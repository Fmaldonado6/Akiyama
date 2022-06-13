package com.fmaldonado.akiyama.data.repositories

import com.fmaldonado.akiyama.data.models.content.Anime
import com.fmaldonado.akiyama.data.models.content.Episode
import com.fmaldonado.akiyama.data.models.content.MainScreenContent
import com.fmaldonado.akiyama.data.models.content.MainScreenContentType
import com.fmaldonado.akiyama.data.network.NetworkDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LatestAnimeRepository
@Inject
constructor(
    private val networkDataSource: NetworkDataSource,
    private val animeRepository: AnimeRepository
) {

    private var latestAnime: List<MainScreenContent>? = null
    private var latestSpecials: List<MainScreenContent>? = null
    private var latestOvas: List<MainScreenContent>? = null
    private var latestMovies: List<MainScreenContent>? = null
    private var latestEpisodes: List<MainScreenContent>? = null


    suspend fun getLatestAnimes(
        shouldFetch: Boolean = false
    ): List<MainScreenContent> {

        if (shouldFetch || latestAnime == null) {
            val animes = networkDataSource.getAnimes()
            latestAnime = animes.map { mapAnimeToMaiContent(it) }
            animeRepository.setInitialSearch(animes)
        }

        return latestAnime ?: mutableListOf()
    }

    suspend fun getLatestSpecials(
        shouldFetch: Boolean = false
    ): List<MainScreenContent> {

        if (shouldFetch || latestSpecials == null) {
            val specials = networkDataSource.getSpecials()
            latestSpecials = specials.map { mapAnimeToMaiContent(it) }
        }

        return latestSpecials ?: mutableListOf()
    }

    suspend fun getLatestOvas(
        shouldFetch: Boolean = false
    ): List<MainScreenContent> {

        if (shouldFetch || latestOvas == null) {
            val ovas = networkDataSource.getOvas()
            latestOvas = ovas.map { mapAnimeToMaiContent(it) }
        }

        return latestOvas ?: mutableListOf()
    }

    suspend fun getLatestMovies(
        shouldFetch: Boolean = false
    ): List<MainScreenContent> {

        if (shouldFetch || latestMovies == null) {
            val movies = networkDataSource.getMovies()
            latestMovies = movies.map { mapAnimeToMaiContent(it) }
        }

        return latestMovies ?: mutableListOf()
    }

    suspend fun getLatestEpisodes(
        shouldFetch: Boolean = false
    ): List<MainScreenContent> {


        if (shouldFetch || latestEpisodes == null) {
            val episodes = networkDataSource.getEpisodes()
            latestEpisodes = episodes.map { mapEpisodeToMaiContent(it) }
        }

        return latestEpisodes ?: mutableListOf()
    }

    private fun mapAnimeToMaiContent(anime: Anime): MainScreenContent {
        return MainScreenContent(
            title = anime.title,
            id = anime.id,
            image = anime.image,
            subtitle = anime.status,
            type = MainScreenContentType.Anime
        )
    }

    private fun mapEpisodeToMaiContent(episode: Episode): MainScreenContent {
        return MainScreenContent(
            title = episode.title,
            id = episode.id,
            image = episode.image,
            subtitle = "En emision",
            type = MainScreenContentType.Episode
        )
    }

}