package com.fmaldonado.akiyama.data.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.fmaldonado.akiyama.data.persistence.entities.FavoritesEntity
import com.fmaldonado.akiyama.data.persistence.entities.FavoritesEpisodesEntity
import com.fmaldonado.akiyama.data.persistence.entities.FavoritesWithEpisodesEntity

@Dao
abstract class FavoritesDao {

    @Transaction
    @Insert
    abstract suspend fun insertFavorites(favorite: FavoritesEntity): Long

    @Transaction
    @Insert
    abstract suspend fun insertFavoritesEpisodes(episodes: List<FavoritesEpisodesEntity>)

    suspend fun insertFullFavorite(favorite: FavoritesWithEpisodesEntity) {
        this.insertFavoritesEpisodes(favorite.episodes)
        this.insertFavorites(favorite.anime)
    }

    @Transaction
    @Query("SELECT * from favorites")
    abstract suspend fun getFavoritesDao(): List<FavoritesWithEpisodesEntity>

}