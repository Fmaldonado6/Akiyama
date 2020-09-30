package com.fmaldonado.akiyama.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.fmaldonado.akiyama.data.models.apiContent.DataTypes
import com.fmaldonado.akiyama.data.models.apiContent.ServerModel
import com.fmaldonado.akiyama.data.models.responses.*
import com.fmaldonado.akiyama.data.network.AruppiApiService

class AruppiRepository(private val apiService: AruppiApiService) {

    private val _latestAnimes = MutableLiveData<AnimeResponse>()
    private val _latestEpisodes = MutableLiveData<EpisodeResponse>()
    private val _latestSpecials = MutableLiveData<SpecialsResponse>()
    private val _latestOvas = MutableLiveData<OvasResponse>()
    private val _latestMovies = MutableLiveData<MoviesResponse>()
    private val _servers = MutableLiveData<ServerResponse>()
    private val _searchResults = MutableLiveData<SearchResponse>()
    private val _animeInfo = MutableLiveData<AnimeInfoResponse>()

    val latestAnimes: LiveData<AnimeResponse>
        get() = _latestAnimes

    val latestEpisodes: LiveData<EpisodeResponse>
        get() = _latestEpisodes

    val latestSpecials: LiveData<SpecialsResponse>
        get() = _latestSpecials

    val latestOvas: LiveData<OvasResponse>
        get() = _latestOvas

    val latestMovies: LiveData<MoviesResponse>
        get() = _latestMovies

    val servers: LiveData<ServerResponse>
        get() = _servers

    val searchResults: LiveData<SearchResponse>
        get() = _searchResults

    val animeInfo: LiveData<AnimeInfoResponse>
        get() = _animeInfo

    suspend fun getData(type: Int) {

        when (type) {
            DataTypes.Episodes.value -> {
                if (_latestEpisodes.value == null) {
                    val episodes = apiService.getEpisodes().await()
                    _latestEpisodes.postValue(episodes)
                }
            }
            DataTypes.Animes.value -> {
                if (_latestAnimes.value == null) {
                    val animes = apiService.getAnimes().await()
                    _latestAnimes.postValue(animes)
                }
            }
            DataTypes.Movies.value -> {
                if (_latestMovies.value == null) {
                    val movies = apiService.getMovies().await()
                    _latestMovies.postValue(movies)
                }
            }
            DataTypes.Specials.value -> {
                if (_latestSpecials.value == null) {
                    val specials = apiService.getSpecials().await()
                    _latestSpecials.postValue(specials)
                }
            }
            DataTypes.Ovas.value -> {
                if (_latestOvas.value == null) {
                    val ovas = apiService.getOvas().await()
                    _latestOvas.postValue(ovas)
                }
            }
        }

    }

    suspend fun getServers(id: String) {
        val stringArray = id.split("/")
        val id = stringArray[0]
        val name = stringArray[1]
        val servers = apiService.getServers(id, name).await()
        _servers.postValue(servers)
    }

    suspend fun getAnimeInfo(animeTitle: String) {
        val title = animeTitle.replace(" ","%20")
        val info = apiService.getAnimeInfo(animeTitle).await()
        _animeInfo.postValue(info)
    }

    suspend fun getSearch(query: String) {
        val search = apiService.getSearch(query).await()
        _searchResults.postValue(search)

    }


}