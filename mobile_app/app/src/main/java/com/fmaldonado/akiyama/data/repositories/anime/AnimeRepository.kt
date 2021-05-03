package com.fmaldonado.akiyama.data.repositories.anime

import androidx.lifecycle.MutableLiveData
import com.fmaldonado.akiyama.data.models.content.Anime
import com.fmaldonado.akiyama.data.models.content.Episode
import com.fmaldonado.akiyama.data.models.content.Server
import com.fmaldonado.akiyama.data.network.NetworkDataSource
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AnimeRepository
@Inject
constructor(
    private val networkDataSource: NetworkDataSource
) {
    var currentSearchQuery:String = ""
    val currentSearch = MutableLiveData<List<Anime>>()
    val latestAnimes = MutableLiveData<List<Anime>>()
    val latestEpisodes = MutableLiveData<List<Episode>>()
    val latestOvas = MutableLiveData<List<Anime>>()
    val latestMovies = MutableLiveData<List<Anime>>()
    val latestSpecials = MutableLiveData<List<Anime>>()

    suspend fun getLatestAnimes() {
        val animes = networkDataSource.getAnimes()
        latestAnimes.postValue(animes)
        currentSearch.postValue(animes)
    }

    suspend fun getLatestEpisodes() {
        val episodes = networkDataSource.getEpisodes()
        latestEpisodes.postValue(episodes)
    }

    suspend fun getLatestOvas() {
        val ovas = networkDataSource.getOvas()
        latestOvas.postValue(ovas)
    }

    suspend fun getLatestMovies() {
        val movies = networkDataSource.getMovies()
        latestMovies.postValue(movies)
    }

    suspend fun getLatestSpecials() {
        val specials = networkDataSource.getSpecials()
        latestSpecials.postValue(specials)
    }

    suspend fun getEpisodeServers(episodeId: String): List<Server> {
        val split = episodeId.split("/")
        return networkDataSource.getServers(split.first(), split.last())
    }

    suspend fun makeSearch(search: String) {
        currentSearchQuery = search
        val searchResults = networkDataSource.getSearch(search)
        this.currentSearch.postValue(searchResults)
    }
}

