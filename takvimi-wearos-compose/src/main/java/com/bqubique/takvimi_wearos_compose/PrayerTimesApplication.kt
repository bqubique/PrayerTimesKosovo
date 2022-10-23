package com.bqubique.takvimi_wearos_compose

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import com.bqubique.takvimi_wearos_compose.services.NotificationService
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PrayerTimesApplication: Application(){
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            NotificationService.PRAYER_TIMES_CHANNEL_ID,
            "Prayer Times",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        channel.description = "Used to fire notifications for and before prayer times"

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}