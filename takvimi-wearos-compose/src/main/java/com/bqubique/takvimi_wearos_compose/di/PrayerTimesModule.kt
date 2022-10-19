package com.bqubique.takvimi_wearos_compose.di

import com.bqubique.takvimi_wearos_compose.api.PrayerTimesApi
import com.bqubique.takvimi_wearos_compose.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class PrayerTimesModule {

    @Provides
    fun provideApi(): PrayerTimesApi =
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PrayerTimesApi::class.java)
}