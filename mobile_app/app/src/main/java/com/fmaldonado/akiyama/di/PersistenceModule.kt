package com.fmaldonado.akiyama.di

import androidx.room.Room
import com.fmaldonado.akiyama.AkiyamaApplication
import com.fmaldonado.akiyama.data.persistence.Database
import com.fmaldonado.akiyama.data.persistence.dao.FavoritesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Singleton
    @Provides
    fun provideDatabase(app: AkiyamaApplication): Database =
        Room.databaseBuilder(app, Database::class.java, Database.DATABASE_NAME)
            .fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideFavoritesDao(db: Database): FavoritesDao = db.favoritesDao()
}