package com.bqubique.wearjetpackcompose.di

import com.bqubique.wearjetpackcompose.api.PrayerTimesApi
import com.bqubique.wearjetpackcompose.util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): OkHttpClient =
        OkHttpClient()
            .newBuilder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()


    @Provides
    @Singleton
    fun provideQuranApi(): PrayerTimesApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(provideLoggingInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PrayerTimesApi::class.java)
    }
}