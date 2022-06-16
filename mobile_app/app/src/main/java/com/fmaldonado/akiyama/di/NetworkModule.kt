package com.fmaldonado.akiyama.di

import com.fmaldonado.akiyama.BuildConfig
import com.fmaldonado.akiyama.data.network.LatestVersionDataSource
import com.fmaldonado.akiyama.data.network.NetworkDataSource
import com.fmaldonado.akiyama.data.network.interceptor.NetworkInterceptor
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Duration
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideAruppiDataSource(networkInterceptor: NetworkInterceptor): NetworkDataSource {
        val specs = listOf(ConnectionSpec.CLEARTEXT, ConnectionSpec.MODERN_TLS)
        val okHttpClient =
            OkHttpClient.Builder()
                .addInterceptor(networkInterceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build()



        return Retrofit.Builder()
            .baseUrl("${BuildConfig.BASE_URL}animes/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NetworkDataSource::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(networkInterceptor: NetworkInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(networkInterceptor).build()
    }

    @Singleton
    @Provides
    fun provideLatestVersionDataSource(networkInterceptor: NetworkInterceptor): LatestVersionDataSource {
        val specs = listOf(ConnectionSpec.CLEARTEXT, ConnectionSpec.MODERN_TLS)
        val okHttpClient =
            OkHttpClient.Builder()
                .addInterceptor(networkInterceptor)
                .build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.GITHUB_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LatestVersionDataSource::class.java)
    }

    @Singleton
    @Provides
    fun provideNetworkInterceptor(): NetworkInterceptor = NetworkInterceptor()

    @Singleton
    @Provides
    fun provideGson(): Gson = Gson()

}