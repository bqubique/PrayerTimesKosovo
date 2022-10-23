package com.bqubique.takvimi_wearos_compose.util

import android.app.AlarmManager
import android.app.PendingIntent
import android.util.Log
import com.bqubique.takvimi_wearos_compose.R
import com.bqubique.takvimi_wearos_compose.view.home.TAG
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import java.util.concurrent.TimeUnit

fun getRandomImage(index: Int): Int {
    val listOfSunriseImages = listOf(
        R.drawable.sunrise_1,
        R.drawable.sunrise_2,
        R.drawable.sunrise_3,
        R.drawable.sunrise_4,
        R.drawable.sunrise_5,
    )
    val listOfMosqueImages = listOf(
        R.drawable.mosque_1,
        R.drawable.mosque_2,
        R.drawable.mosque_3,
        R.drawable.mosque_4,
        R.drawable.mosque_5,
    )
    val listOfSunsetImages = listOf(
        R.drawable.maghrib_1,
        R.drawable.maghrib_2,
        R.drawable.maghrib_3,
        R.drawable.maghrib_4,
        R.drawable.maghrib_5,
    )
    val listOfNightImages = listOf(
        R.drawable.night_1,
        R.drawable.night_2,
        R.drawable.night_3,
        R.drawable.night_4,
    )
    return when (index) {
        0, 1 -> {
            listOfSunriseImages[(0..4).random()]
        }
        2, 3 -> {
            listOfMosqueImages[(0..4).random()]
        }
        4 -> {
            listOfSunsetImages[(0..4).random()]
        }
        else -> listOfNightImages[(0..3).random()]
    }
}

fun getPrayerName(index: Int): String = when (index) {
    0 -> "Imsaku"
    1 -> "Sabahu"
    2 -> "Dreka"
    3 -> "Ikindia"
    4 -> "Akshami"
    else -> "Jacia"
}

fun getNextPrayer(prayerTimes: List<String?>): Int {
    for (i in 0..5) {
        val millis = Duration.between(
            LocalDateTime.now(), LocalDateTime.of(
                LocalDate.now().year,
                LocalDate.now().monthValue,
                LocalDate.now().dayOfMonth,
                prayerTimes[i]?.substring(0, 2)?.toInt()!!,
                prayerTimes[i]?.substring(5)?.toInt()!!,
            )
        ).toMillis()
        if (millis > 0) return i
    }
    return -1
}

fun getTimeLeft(prayerTimes: List<String?>): String {
    for (i: Int in 0..5) {
        val millis = Duration.between(
            LocalDateTime.now(), LocalDateTime.of(
                LocalDate.now().year,
                LocalDate.now().monthValue,
                LocalDate.now().dayOfMonth,
                prayerTimes[i]?.substring(0, 2)?.toInt()!!,
                prayerTimes[i]?.substring(5)?.toInt()!!,
            )
        ).toMillis()


        if (millis > 0) {
            if (TimeUnit.MILLISECONDS.toHours(millis) > 0) {
                return String.format(
                    "%2dhr %02dmin ",
                    TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(
                        TimeUnit.MILLISECONDS.toHours(millis)
                    ),
                )
            }
            //TODO: Check when 00min
            return String.format(
                "%02dmin ",
                TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(
                    TimeUnit.MILLISECONDS.toHours(millis)
                ),
            )
        }
    }
    return "😴"
}

fun setAlarms(
    prayerTimes: List<String?>,
    alarmManager: AlarmManager,
    pendingIntentMap: Map<String, PendingIntent>,
) {
    val listOfCalendars = mutableListOf<Calendar>()

    for (i in prayerTimes.indices) {
        listOfCalendars.add(Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, prayerTimes[i]?.substring(0, 2)?.toInt()!!)
            set(Calendar.MINUTE, prayerTimes[i]?.substring(5)?.toInt()!!)
            set(Calendar.SECOND, 0)
        })
    }

    alarmManager.setExactAndAllowWhileIdle(
        AlarmManager.RTC_WAKEUP, listOfCalendars[1].timeInMillis, pendingIntentMap["sunrise"]
    )
    alarmManager.setExactAndAllowWhileIdle(
        AlarmManager.RTC_WAKEUP,
        listOfCalendars[1].timeInMillis - 900000,
        pendingIntentMap["preSunrise"]
    )
    alarmManager.setExactAndAllowWhileIdle(
        AlarmManager.RTC_WAKEUP, listOfCalendars[2].timeInMillis, pendingIntentMap["dhuhr"]
    )
    alarmManager.setExactAndAllowWhileIdle(
        AlarmManager.RTC_WAKEUP,
        listOfCalendars[2].timeInMillis - 900000,
        pendingIntentMap["preDhuhr"]
    )
    alarmManager.setExactAndAllowWhileIdle(
        AlarmManager.RTC_WAKEUP, listOfCalendars[3].timeInMillis, pendingIntentMap["asr"]
    )
    alarmManager.setExactAndAllowWhileIdle(
        AlarmManager.RTC_WAKEUP,
        listOfCalendars[3].timeInMillis - 900000,
        pendingIntentMap["preAsr"]
    )
    alarmManager.setExactAndAllowWhileIdle(
        AlarmManager.RTC_WAKEUP, listOfCalendars[4].timeInMillis, pendingIntentMap["maghrib"]
    )
    alarmManager.setExactAndAllowWhileIdle(
        AlarmManager.RTC_WAKEUP,
        listOfCalendars[4].timeInMillis - 900000,
        pendingIntentMap["preMaghrib"]
    )
    alarmManager.setExactAndAllowWhileIdle(
        AlarmManager.RTC_WAKEUP, listOfCalendars[5].timeInMillis, pendingIntentMap["isha"]
    )
    alarmManager.setExactAndAllowWhileIdle(
        AlarmManager.RTC_WAKEUP,
        listOfCalendars[5].timeInMillis - 900000,
        pendingIntentMap["preIsha"]
    )

    Log.i(TAG, "setAlarms: Intents set for all prayer times and pre-prayer times.")
}

fun cancelAlarms(
    alarmManager: AlarmManager,
    pendingIntentMap: Map<String, PendingIntent>,
) {
    alarmManager.cancel(pendingIntentMap["sunrise"])
    alarmManager.cancel(pendingIntentMap["preSunrise"])
    alarmManager.cancel(pendingIntentMap["dhuhr"])
    alarmManager.cancel(pendingIntentMap["preDhuhr"])
    alarmManager.cancel(pendingIntentMap["asr"])
    alarmManager.cancel(pendingIntentMap["preAsr"])
    alarmManager.cancel(pendingIntentMap["maghrib"])
    alarmManager.cancel(pendingIntentMap["preMaghrib"])
    alarmManager.cancel(pendingIntentMap["isha"])
    alarmManager.cancel(pendingIntentMap["preIsha"])
    Log.i(TAG, "setAlarms: All intents canceled.")
}
