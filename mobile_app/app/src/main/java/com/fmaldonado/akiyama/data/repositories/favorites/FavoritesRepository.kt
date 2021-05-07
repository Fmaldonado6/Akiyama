package com.fmaldonado.akiyama.data.repositories.favorites

import androidx.lifecycle.MutableLiveData
import com.fmaldonado.akiyama.data.models.content.Anime
import com.fmaldonado.akiyama.data.persistence.dao.FavoritesDao
import com.fmaldonado.akiyama.data.persistence.mappers.FavoritesMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoritesRepository
@Inject
constructor(
    private val favoritesDao: FavoritesDao
) {
    private var _favorites = mutableListOf<Anime>()
    val favorites = MutableLiveData<List<Anime>>()

    suspend fun addFavorite(anime: Anime) {
        val favorite = FavoritesMapper.animeToFavoritesMapper(anime)
        favoritesDao.insertFullFavorite(favorite)
        _favorites.add(anime)
        favorites.postValue(_favorites)
    }

    suspend fun removeFavorite(anime: Anime) {
        val favorite = FavoritesMapper.animeToFavoritesMapper(anime)
        favoritesDao.removeFavorite(favorite.anime)
        _favorites = _favorites.filter { s -> s.id != anime.id }.toMutableList()
        favorites.postValue(_favorites)
    }


    suspend fun isFavorite(id: String): Boolean {
        return favoritesDao.favoriteExists(id) != null
    }

    suspend fun loadFavorites() {
        val favorites = favoritesDao.getFavoritesDao()
        val animes = FavoritesMapper.favoritesListToAnimeListMapper(favorites)
        _favorites.addAll(animes)
        this.favorites.postValue(_favorites)
    }

}