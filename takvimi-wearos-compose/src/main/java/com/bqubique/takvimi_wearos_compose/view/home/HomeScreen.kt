package com.bqubique.takvimi_wearos_compose.view.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material.*
import com.bqubique.takvimi_wearos_compose.R
import com.bqubique.takvimi_wearos_compose.view.theme.md_theme_dark_primary
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

@Composable
fun HomeScreen(
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
) {
    val currentDate = LocalDateTime.now()

    val isLoading = homeScreenViewModel.loading.observeAsState()
    val listOfPrayerTimes = homeScreenViewModel.listOfPrayerTimes.observeAsState()

    val dateTimeFormatter = DateTimeFormatter.ofPattern("EEE\ndd MMM yyyy")

    if (!isLoading.value!!) {
        Scaffold(vignette = { Vignette(vignettePosition = VignettePosition.TopAndBottom) },
            timeText = {
                TimeText(startLinearContent = {
                    Text(
                        renderCorrectTime(
                            prayerTimes = listOfPrayerTimes.value!!
                        ), color = MaterialTheme.colors.primary
                    )
                }, startCurvedContent = {
                    curvedText(
                        renderCorrectTime(
                            prayerTimes = listOfPrayerTimes.value!!
                        ), color = md_theme_dark_primary
                    )
                })
            }) {

            ScalingLazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    Text(
                        dateTimeFormatter.format(currentDate),
                        modifier = Modifier.padding(bottom = 10.dp, top = 30.dp),
                        textAlign = TextAlign.Center,
                        style = TextStyle(fontWeight = FontWeight.Bold)
                    )
                }
                itemsIndexed(listOfPrayerTimes.value!!) { index, _ ->
                    TitleCard(
                        modifier = Modifier.height(70.dp),
                        onClick = { },
                        title = { Text(getPrayerName(index)) },
                        backgroundPainter = if (getNextPrayer(prayerTimes = listOfPrayerTimes.value!!) == index) CardDefaults.imageWithScrimBackgroundPainter(
                            backgroundImagePainter = painterResource(id = getRandomImage(index)),
                            backgroundImageScrimBrush = Brush.horizontalGradient(
                                listOf(
                                    MaterialTheme.colors.onPrimary.copy(alpha = 0.7f),
                                    Color.Transparent
                                )
                            )
                        ) else CardDefaults.imageWithScrimBackgroundPainter(
                            backgroundImagePainter = painterResource(id = getRandomImage(index)),
                            backgroundImageScrimBrush = Brush.horizontalGradient(
                                listOf(
                                    Color.Black.copy(alpha = 0.7f),
                                    Color.Transparent,
                                )
                            )
                        ),
                        contentColor = MaterialTheme.colors.onSurface,
                        titleColor = MaterialTheme.colors.onSurface,
                        time = {
                            listOfPrayerTimes.value!![index]?.let {
                                Text(
                                    it.replace(
                                        " ", ""
                                    )
                                )
                            }
                        },
                    ) {}
                }
            }
        }
    } else {
        CircularProgressIndicator()
    }
}

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

fun renderCorrectTime(prayerTimes: List<String?>): String {
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
