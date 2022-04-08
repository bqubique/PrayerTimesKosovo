package com.bqubique.wearjetpackcompose.view

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.*
import com.bqubique.wearjetpackcompose.R
import com.bqubique.wearjetpackcompose.viewmodel.PrayerTimesViewModel

@OptIn(ExperimentalWearMaterialApi::class)
@Composable
fun PrayerTimesList(
    prayerTimesViewModel: PrayerTimesViewModel,
    scalingLazyListState: ScalingLazyListState
) {
    val formFactor = stringResource(id = R.string.form_factor)
    val prayerTimes = prayerTimesViewModel.prayerTimes.observeAsState()
    val loading = prayerTimesViewModel.loading.observeAsState()
    val nextPrayer = prayerTimesViewModel.nextPrayer.observeAsState()

    Scaffold(
        vignette = { Vignette(vignettePosition = VignettePosition.TopAndBottom) },
        timeText = { TimeText() },
        positionIndicator = { PositionIndicator(scalingLazyListState = scalingLazyListState) }
    ) {
        Log.d("PrayerTimesList", "PrayerTimesList: ${nextPrayer.value}")

        if (loading.value!!)
            CircularProgressIndicator(
                modifier = Modifier.fillMaxSize(),
                trackColor = Color.Black,
                strokeWidth = 10.dp,
                indicatorColor = Color(0xffffc400),
            )
        else
            ScalingLazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                state = scalingLazyListState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 0.dp, bottom = 0.dp),
            ) {

                item {
                    TitleCard(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 10.dp, end = 10.dp, top = 40.dp, bottom = 10.dp),
                        onClick = {},
//                        backgroundPainter = CardDefaults.imageWithScrimBackgroundPainter(
//                            backgroundImagePainter = painterResource(id = R.drawable.sunset),
//                        ),
                        backgroundPainter = if (nextPrayer.value == 0) CardDefaults.imageWithScrimBackgroundPainter(painterResource(id = R.drawable.sunset)) else CardDefaults.cardBackgroundPainter(),
                        time = { Text("Imsaku") },
                        title = {
                            Text(
                                text = prayerTimes.value?.get(0)?.imsaku.toString(),
                                color = Color(0xffffc400)
                            )
                        },
                    ) {}
                }
                item {
                    TitleCard(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
                        onClick = {},
                        backgroundPainter = if (nextPrayer.value == 1) CardDefaults.imageWithScrimBackgroundPainter(painterResource(id = R.drawable.sunset)) else CardDefaults.cardBackgroundPainter(),
                        time = { Text("Sabahu") },
                        title = {
                            Text(
                                text = prayerTimes.value?.get(0)?.sabahu.toString(),
                                color = Color(0xffffc400)
                            )
                        },
                    ) {}
                }
                item {
                    TitleCard(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
                        onClick = {},
                        backgroundPainter = if (nextPrayer.value == 2) CardDefaults.imageWithScrimBackgroundPainter(painterResource(id = R.drawable.sunset)) else CardDefaults.cardBackgroundPainter(),
                        time = { Text("Lindja e Diellit") },
                        title = {
                            Text(
                                text = prayerTimes.value?.get(0)?.lindjaDiellit.toString(),
                                color = Color(0xffffc400)
                            )
                        },
                    ) {}
                }
                item {
                    TitleCard(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
                        onClick = {},
                        backgroundPainter = if (nextPrayer.value == 3) CardDefaults.imageWithScrimBackgroundPainter(painterResource(id = R.drawable.sunset)) else CardDefaults.cardBackgroundPainter(),
                        time = { Text("Dreka") },
                        title = {
                            Text(
                                text = prayerTimes.value?.get(0)?.dreka.toString(),
                                color = Color(0xffffc400)
                            )
                        },
                    ) {}
                }
                item {
                    TitleCard(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
                        onClick = {},
                        backgroundPainter = if (nextPrayer.value == 4) CardDefaults.imageWithScrimBackgroundPainter(painterResource(id = R.drawable.sunset)) else CardDefaults.cardBackgroundPainter(),
                        time = { Text("Ikindia") },
                        title = {
                            Text(
                                text = prayerTimes.value?.get(0)?.ikindia.toString(),
                                color = Color(0xffffc400)
                            )
                        },
                    ) {}
                }
                item {
                    TitleCard(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
                        onClick = {},
                        backgroundPainter = if (nextPrayer.value == 5) CardDefaults.imageWithScrimBackgroundPainter(painterResource(id = R.drawable.sunset)) else CardDefaults.cardBackgroundPainter(),
                        time = { Text("Akshami") },
                        title = {
                            Text(
                                text = prayerTimes.value?.get(0)?.akshami.toString(),
                                color = Color(0xffffc400)
                            )
                        },
                    ) {}
                }
                item {
                    TitleCard(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 10.dp, end = 10.dp, bottom = 0.dp),
                        onClick = {},
                        backgroundPainter = if (nextPrayer.value == 6) CardDefaults.imageWithScrimBackgroundPainter(painterResource(id = R.drawable.sunset)) else CardDefaults.cardBackgroundPainter(),

                        time = { Text("Jacia") },
                        title = {
                            Text(
                                text = prayerTimes.value?.get(0)?.jacia.toString(),
                                color = Color(0xffffc400)
                            )
                        },
                    ) {}
                }
            }
    }
}

