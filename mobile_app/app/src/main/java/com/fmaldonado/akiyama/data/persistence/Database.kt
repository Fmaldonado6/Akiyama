package com.fmaldonado.akiyama.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fmaldonado.akiyama.data.persistence.converters.StringListConverter
import com.fmaldonado.akiyama.data.persistence.dao.FavoritesDao
import com.fmaldonado.akiyama.data.persistence.entities.FavoritesEntity

@Database(
    version = 3,
    entities = [
        FavoritesEntity::class
    ]
)
@TypeConverters(StringListConverter::class)
abstract class Database : RoomDatabase() {

    abstract fun favoritesDao(): FavoritesDao

    companion object {
        val DATABASE_NAME = "akiyama_db"
    }

}