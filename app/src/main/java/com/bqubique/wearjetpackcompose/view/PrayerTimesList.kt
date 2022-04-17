package com.bqubique.wearjetpackcompose.view

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
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
import com.bqubique.wearjetpackcompose.model.PrayerTimes
import com.bqubique.wearjetpackcompose.viewmodel.PrayerTimesViewModel

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
                        backgroundPainter = CardDefaults.cardBackgroundPainter(),
                        time = { Text("Imsaku") },
                        title = {
                            Text(
                                text = prayerTimes.value?.get(0)?.imsaku.toString(),
                                color = Color(0xffffc400)
                            )
                        },
                    ) {}
                }
                items(6) { count ->
                    PrayerTimeCard(
                        nextPrayer = nextPrayer,
                        prayerTime = when (count) {
                            0 -> prayerTimes.value?.get(0)?.sabahu.toString()
                            1 -> prayerTimes.value?.get(0)?.lindjaDiellit.toString()
                            2 -> prayerTimes.value?.get(0)?.dreka.toString()
                            3 -> prayerTimes.value?.get(0)?.ikindia.toString()
                            4 -> prayerTimes.value?.get(0)?.akshami.toString()
                            else -> prayerTimes.value?.get(0)?.jacia.toString()
                        },
                        count = count,
                        title = when (count) {
                            0 -> "Sabahu"
                            1 -> "Lindja e Diellit"
                            2 -> "Dreka"
                            3 -> "Ikindia"
                            4 -> "Akshami"
                            else -> "Jacia"
                        },
                    )
                }
            }
    }
}

@Composable
fun PrayerTimeCard(
    nextPrayer: State<Int?>,
    prayerTime: String,
    count: Int,
    title: String
) {
    return TitleCard(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
            .height(if (nextPrayer.value == count) 90.dp else 45.dp),
        onClick = {},
        backgroundPainter = if (nextPrayer.value == count) CardDefaults.imageWithScrimBackgroundPainter(
            painterResource(id = R.drawable.sunset)
        ) else CardDefaults.cardBackgroundPainter(),
        time = { Text(title) },
        title = {
            Text(
                text = prayerTime,
                color = Color(0xffffc400)
            )
        },
    ) {}
}

