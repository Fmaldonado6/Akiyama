package com.fmaldonado.akiyama.data.persistence.mappers

import com.fmaldonado.akiyama.data.models.content.Anime
import com.fmaldonado.akiyama.data.models.content.Episode
import com.fmaldonado.akiyama.data.persistence.entities.FavoritesEntity

object FavoritesMapper {

    fun favoritesListToAnimeListMapper(favorites: List<FavoritesEntity>): List<Anime> {
        return favorites.map { this.favoriteToAnimeMapper(it) }
    }

    fun animeToFavoritesMapper(anime: Anime): FavoritesEntity {
        return FavoritesEntity(
            id = anime.id,
            title = anime.title,
            poster = anime.image,
            type = anime.type,
            genres = anime.genres
        )

    }

    fun favoriteToAnimeMapper(favorite: FavoritesEntity): Anime {
        return Anime(
            id = favorite.id,
            title = favorite.title,
            type = favorite.type,
            image = favorite.poster,
            genres = favorite.genres,
        )
    }

}