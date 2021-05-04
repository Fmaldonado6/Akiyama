package com.fmaldonado.akiyama.data.persistence.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

data class FavoritesWithEpisodesEntity(
    @Embedded val anime: FavoritesEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "animeId"
    )
    val episodes: List<FavoritesEpisodesEntity>
)