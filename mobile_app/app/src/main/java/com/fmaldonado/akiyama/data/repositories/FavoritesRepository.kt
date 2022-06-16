package com.fmaldonado.akiyama.data.repositories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.fmaldonado.akiyama.data.models.content.Anime
import com.fmaldonado.akiyama.data.persistence.dao.FavoritesDao
import com.fmaldonado.akiyama.data.persistence.mappers.FavoritesMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoritesRepository
@Inject
constructor(
    private val favoritesDao: FavoritesDao
) {

    private val favoritesFlow = MutableStateFlow<List<Anime>>(mutableListOf())

    val favoritesList = favoritesFlow.asLiveData()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            loadFavorites()
        }
    }

    suspend fun addFavorite(anime: Anime) {
        val favorite = FavoritesMapper.animeToFavoritesMapper(anime)
        favoritesDao.insertFavorites(favorite)
        loadFavorites()
    }

    suspend fun removeFavorite(anime: Anime) {
        val favorite = FavoritesMapper.animeToFavoritesMapper(anime)
        favoritesDao.removeFavorite(favorite)
        loadFavorites()
    }

    suspend fun isFavorite(id: String): Boolean {
        return favoritesDao.favoriteExists(id) != null
    }

    private suspend fun loadFavorites() {
        favoritesFlow.value = favoritesDao.getFavorites().map {
            FavoritesMapper.favoriteToAnimeMapper(it)
        }
    }

}