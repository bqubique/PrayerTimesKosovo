package com.bqubique.takvimi_wearos_compose.view.home

import android.app.AlarmManager
import android.app.PendingIntent
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.wear.compose.material.*
import com.bqubique.takvimi_wearos_compose.util.*
import com.bqubique.takvimi_wearos_compose.view.theme.md_theme_dark_primary
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

const val TAG = "HomeScreen"

@Composable
fun HomeScreen(
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel(),
    scalingLazyListState: ScalingLazyListState,
    alarmManager: AlarmManager,
    pendingIntentMap: Map<String, PendingIntent>,
    navController: NavHostController = rememberNavController(),
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
                        getTimeLeft(
                            prayerTimes = listOfPrayerTimes.value!!
                        ), color = MaterialTheme.colors.primary
                    )
                }, startCurvedContent = {
                    curvedText(
                        getTimeLeft(
                            prayerTimes = listOfPrayerTimes.value!!
                        ), color = md_theme_dark_primary
                    )
                })
            }) {
            LaunchedEffect(Unit) {
//                setAlarms(
//                    prayerTimes = listOfPrayerTimes.value!!,
//                    alarmManager = alarmManager,
//                    pendingIntentMap = pendingIntentMap,
//                )
            }
            ScalingLazyColumn(
                modifier = Modifier.fillMaxSize(),
                state = scalingLazyListState,
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
                        modifier = Modifier.height(if (getNextPrayer(prayerTimes = listOfPrayerTimes.value!!) == index) 100.dp else 70.dp),
                        onClick = { },
                        title = {
                            Text(
                                getPrayerName(index),
                                style = if (getNextPrayer(prayerTimes = listOfPrayerTimes.value!!) == index) TextStyle(
                                    color = MaterialTheme.colors.onPrimary,
                                    fontWeight = FontWeight.Bold,
                                ) else TextStyle()
                            )
                        },
                        backgroundPainter = if (getNextPrayer(prayerTimes = listOfPrayerTimes.value!!) == index) CardDefaults.imageWithScrimBackgroundPainter(
                            backgroundImagePainter = painterResource(id = getRandomImage(index)),
                            backgroundImageScrimBrush = Brush.horizontalGradient(
                                listOf(
                                    MaterialTheme.colors.primary,
                                    MaterialTheme.colors.primary.copy(alpha = 0.7f),
                                    Color.Transparent
                                )
                            )
                        ) else CardDefaults.imageWithScrimBackgroundPainter(
                            backgroundImagePainter = painterResource(id = getRandomImage(index)),
                            backgroundImageScrimBrush = Brush.horizontalGradient(
                                listOf(
                                    Color.Black.copy(alpha = 0.7f),
                                    Color.Black.copy(alpha = 0.4f),
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
                                    ),
                                    style = if (getNextPrayer(prayerTimes = listOfPrayerTimes.value!!) == index) TextStyle(
                                        color = MaterialTheme.colors.onPrimary,
                                        fontWeight = FontWeight.Bold
                                    ) else TextStyle()
                                )
                            }
                        },
                    ) {}
                }
                item{
                    Button(onClick = {
                        navController.navigate("settings_screen")
                    }) {
                        Icon(Icons.Rounded.Settings, contentDescription = "Settings Screen")
                    }
                }
            }
        }
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}