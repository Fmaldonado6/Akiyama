package com.fmaldonado.akiyama.data.persistence.dao

import androidx.room.*
import com.fmaldonado.akiyama.data.persistence.entities.FavoritesEntity
import com.fmaldonado.akiyama.data.persistence.entities.FavoritesEpisodesEntity
import com.fmaldonado.akiyama.data.persistence.entities.FavoritesWithEpisodesEntity

@Dao
abstract class FavoritesDao {

    @Transaction
    @Insert
    abstract suspend fun insertFavorites(favorite: FavoritesEntity): Long

    @Transaction
    @Delete
    abstract suspend fun removeFavorite(favorite: FavoritesEntity): Int

    @Transaction
    @Query("SELECT id from favorites where id=:id")
    abstract suspend fun favoriteExists(id: String): String?

    @Transaction
    @Insert
    abstract suspend fun insertFavoritesEpisodes(episodes: List<FavoritesEpisodesEntity>)

    suspend fun insertFullFavorite(favorite: FavoritesWithEpisodesEntity) {
        this.insertFavorites(favorite.anime)
        this.insertFavoritesEpisodes(favorite.episodes)
    }

    @Transaction
    @Query("SELECT * from favorites")
    abstract suspend fun getFavoritesDao(): List<FavoritesWithEpisodesEntity>

}