package com.bqubique.wearjetpackcompose.api

import com.bqubique.wearjetpackcompose.model.PrayerTimes
import retrofit2.Response
import retrofit2.http.GET

interface PrayerTimesApi {
    @GET("takvimiApiKosove.php")
    suspend fun getRandomAyah(): Response<PrayerTimes>
}