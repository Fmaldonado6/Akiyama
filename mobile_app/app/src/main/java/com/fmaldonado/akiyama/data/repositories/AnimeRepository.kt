package com.fmaldonado.akiyama.data.repositories

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
    var currentSearchQuery: String = ""
    private var initialSearch: List<Anime>? = null;
    private var currentSearch: List<Anime>? = null

    suspend fun getAnimeInfo(anime: Anime): Anime {
        return networkDataSource.getAnimeInfo(anime.id)
    }

    suspend fun makeSearch(search: String) {
        currentSearchQuery = search
        val searchResults = networkDataSource.getSearch(search)
    }

    fun setInitialSearch(animes: List<Anime>) {
        initialSearch = animes
    }

    suspend fun getCurrentSearch(): List<Anime> {

        if (currentSearch == null && initialSearch != null)
            currentSearch = initialSearch

        if (currentSearch == null && initialSearch == null) {
            initialSearch = networkDataSource.getAnimes()
            currentSearch = initialSearch
        }

        return currentSearch ?: mutableListOf()
    }
}

