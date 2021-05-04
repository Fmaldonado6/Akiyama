package com.fmaldonado.akiyama.data.persistence.entities

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import org.jetbrains.annotations.NotNull

@Entity(
    tableName = "favoritesEpisodes",

    )
data class FavoritesEpisodesEntity(
    @PrimaryKey(autoGenerate = false)
    @NotNull
    @ColumnInfo(name = "id")
    val id: String = "",
    @ColumnInfo(name = "animeId")
    val animeId: String = "",
    @NotNull
    @ColumnInfo(name = "title")
    val title: String = "",
)