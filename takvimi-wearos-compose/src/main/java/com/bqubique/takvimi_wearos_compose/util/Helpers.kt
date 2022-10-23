package com.bqubique.takvimi_wearos_compose.util

import com.bqubique.takvimi_wearos_compose.R
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
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
