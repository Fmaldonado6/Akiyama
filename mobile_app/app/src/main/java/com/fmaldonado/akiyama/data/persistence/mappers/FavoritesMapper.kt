package com.fmaldonado.akiyama.data.persistence.mappers

import com.fmaldonado.akiyama.data.models.content.Anime
import com.fmaldonado.akiyama.data.models.content.Episode
import com.fmaldonado.akiyama.data.persistence.entities.FavoritesEntity
import com.fmaldonado.akiyama.data.persistence.entities.FavoritesEpisodesEntity
import com.fmaldonado.akiyama.data.persistence.entities.FavoritesWithEpisodesEntity

object FavoritesMapper {

    fun favoritesListToAnimeListMapper(favorites: List<FavoritesWithEpisodesEntity>): List<Anime> {
        return favorites.map { this.favoriteToAnimeMapper(it) }
    }

    fun animeToFavoritesMapper(anime: Anime): FavoritesWithEpisodesEntity {

        val favorite = FavoritesEntity(
            id = anime.id,
            title = anime.title,
            rating = anime.rating,
            debut = anime.status,
            poster = anime.image,
            synopsis = anime.synopsis,
            type = anime.type,
            genres = anime.genres
        )

        val episodes = mutableListOf<FavoritesEpisodesEntity>()

        anime.episodes.forEach {
            if (it.id.isNotEmpty())
                episodes.add(
                    FavoritesEpisodesEntity(
                        id = it.id,
                        animeId = anime.id,
                        title = anime.title,
                        episode = it.episode
                    )
                )
        }

        return FavoritesWithEpisodesEntity(favorite, episodes)

    }

    private fun favoriteToAnimeMapper(favorite: FavoritesWithEpisodesEntity): Anime {

        val episodes = mutableListOf<Episode>()

        favorite.episodes.forEach {
            episodes.add(
                Episode(
                    id = it.id,
                    title = it.title,
                    episode = it.episode
                )
            )
        }

        return Anime(
            id = favorite.anime.id,
            title = favorite.anime.title,
            type = favorite.anime.type,
            status = favorite.anime.debut,
            synopsis = favorite.anime.synopsis,
            image = favorite.anime.poster,
            rating = favorite.anime.rating,
            episodes = episodes,
            genres = favorite.anime.genres,
            nextEpisodeDate = ""
        )


    }

}