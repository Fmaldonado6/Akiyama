package com.fmaldonado.akiyama.data.persistence.entities

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import org.jetbrains.annotations.NotNull

@Entity(
    tableName = "favoritesEpisodes",
    indices = [Index(value = ["animeId"])],
    foreignKeys = [
        ForeignKey(
            entity = FavoritesEntity::class,
            parentColumns = ["id"],
            childColumns = ["animeId"],
            onDelete = CASCADE,
            onUpdate = CASCADE
        )
    ]
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
    @NotNull
    @ColumnInfo(name = "episode")
    val episode: Float = 0f,
)