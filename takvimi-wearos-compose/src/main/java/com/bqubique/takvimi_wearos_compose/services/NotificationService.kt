package com.bqubique.takvimi_wearos_compose.services

import android.app.NotificationManager
import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import com.bqubique.takvimi_wearos_compose.R


class NotificationService(
    private val context: Context
) {
    val TAG = "NotificationsService"
    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun showNotification(title: String, content: String) {
        val notification = NotificationCompat.Builder(context, PRAYER_TIMES_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_wb_sunny_24)
            .setContentTitle(title)
            .setContentText(content)
            .build()
        notificationManager.notify(1, notification)
    }

    companion object {
        const val PRAYER_TIMES_CHANNEL_ID = "prayer_times_channel"
    }
}