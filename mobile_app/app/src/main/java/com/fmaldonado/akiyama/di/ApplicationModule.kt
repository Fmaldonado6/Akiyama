package com.fmaldonado.akiyama.di

import android.content.Context
import com.fmaldonado.akiyama.AkiyamaApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): AkiyamaApplication =
        app as AkiyamaApplication

}