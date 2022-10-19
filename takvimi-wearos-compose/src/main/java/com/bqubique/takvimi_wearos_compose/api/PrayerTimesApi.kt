package com.bqubique.takvimi_wearos_compose.api

import com.bqubique.takvimi_wearos_compose.model.PrayerTimes
import retrofit2.Response
import retrofit2.http.GET

interface PrayerTimesApi {
    @GET("data.php?_=1666107485359&dataG=17/01/1998")
    suspend fun getPrayerTimes(): Response<PrayerTimes>
}