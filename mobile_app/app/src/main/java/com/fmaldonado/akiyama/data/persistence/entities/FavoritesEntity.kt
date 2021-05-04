package com.fmaldonado.akiyama.data.persistence.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fmaldonado.akiyama.data.models.content.Episode
import org.jetbrains.annotations.NotNull

@Entity(tableName = "favorites")
data class FavoritesEntity(
    @PrimaryKey(autoGenerate = false)
    @NotNull
    @ColumnInfo(name = "id")
    val id: String = "",
    @NotNull
    @ColumnInfo(name = "title")
    val title: String = "",
    @NotNull
    @ColumnInfo(name = "type")
    val type: String = "",
    @NotNull
    @ColumnInfo(name = "poster")
    val poster: String = "",
    @NotNull
    @ColumnInfo(name = "synopsis")
    val synopsis: String = "",
    @NotNull
    @ColumnInfo(name = "debut")
    val debut: String = "",
    @NotNull
    @ColumnInfo(name = "rating")
    val rating: String = "",
)
