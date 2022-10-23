package com.bqubique.takvimi_wearos_compose.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

const val TAG = "AlarmReceiver"

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {

        val notificationService = NotificationService(p0!!)

        if (p1?.extras?.getInt("prayerTimeId") == 0) {
            notificationService.showNotification(title = "Sunrise", "Finish up, the sun is rising 🌄.")
        } else if (p1?.extras?.getInt("prayerTimeId") == 1) {
            notificationService.showNotification(title = "Sunrise", "15 minutes to Sunrise.")
        } else if (p1?.extras?.getInt("prayerTimeId") == 2) {
            notificationService.showNotification(title = "Dhuhr", "Time to pray Dhuhr.")
        } else if (p1?.extras?.getInt("prayerTimeId") == 3) {
            notificationService.showNotification(title = "Dhuhr", "15 minutes to Dhuhr.")
        } else if (p1?.extras?.getInt("prayerTimeId") == 4) {
            notificationService.showNotification(title = "Asr", "Time to pray Asr.")
        } else if (p1?.extras?.getInt("prayerTimeId") == 5) {
            notificationService.showNotification(title = "Asr", "15 minutes to Asr.")
        } else if (p1?.extras?.getInt("prayerTimeId") == 6) {
            notificationService.showNotification(title = "Maghrib", "Time to pray Maghrib.")
        } else if (p1?.extras?.getInt("prayerTimeId") == 7) {
            notificationService.showNotification(title = "Maghrib", "15 minutes to Maghrib.")
        } else if (p1?.extras?.getInt("prayerTimeId") == 8) {
            notificationService.showNotification(title = "Isha", "Time for Isha.")
        } else if (p1?.extras?.getInt("prayerTimeId") == 9) {
            notificationService.showNotification(title = "Isha", "15 minutes to Isha.")
        }
    }
}