package com.fmaldonado.akiyama.di

import com.fmaldonado.akiyama.BuildConfig
import com.fmaldonado.akiyama.data.network.AruppiDataSource
import com.fmaldonado.akiyama.data.network.interceptor.NetworkInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideAruppiDataSource(networkInterceptor: NetworkInterceptor): AruppiDataSource {
        val specs = listOf(ConnectionSpec.CLEARTEXT, ConnectionSpec.MODERN_TLS)
        val okHttpClient =
            OkHttpClient.Builder()
                .addInterceptor(networkInterceptor)
                .build()

        return Retrofit.Builder()
            .baseUrl("${BuildConfig.BASE_URL}animes/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AruppiDataSource::class.java)
    }

    @Singleton
    @Provides
    fun provideNetworkInterceptor(): NetworkInterceptor = NetworkInterceptor()

}