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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.*
import com.bqubique.wearjetpackcompose.R
import com.bqubique.wearjetpackcompose.viewmodel.PrayerTimesViewModel
import java.util.*

@OptIn(ExperimentalWearMaterialApi::class)
@Composable
fun PrayerTimesList(prayerTimesViewModel: PrayerTimesViewModel, scalingLazyListState: ScalingLazyListState) {
    val formFactor = stringResource(id = R.string.form_factor)
    val prayerTimes = prayerTimesViewModel.prayerTimes.observeAsState()
    val loading = prayerTimesViewModel.loading.observeAsState()
//    val scalingLazyListState = rememberScalingLazyListState()

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
                    AppCard(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 10.dp, end = 10.dp, top = 40.dp, bottom = 10.dp),
                        onClick = { /*TODO*/ },
                        appName = { Text("Imsaku") },
                        time = { },
                        title = {
                            Text(
                                text = prayerTimes.value?.get(0)?.imsaku.toString(),
                                color = Color(0xffffc400)
                            )
                        },
                    ) {}
                }
                item {
                    AppCard(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
                        onClick = { /*TODO*/ },
                        appName = { Text("Sabahu") },
                        time = { },
                        title = {
                            Text(
                                text = prayerTimes.value?.get(0)?.sabahu.toString(),
                                color = Color(0xffffc400)
                            )
                        },
                    ) {}
                }
                item {
                    AppCard(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
                        onClick = { /*TODO*/ },
                        appName = { Text("Lindja e Diellit") },
                        time = { },
                        title = {
                            Text(
                                prayerTimes.value?.get(0)?.lindjaDiellit.toString(),
                                color = Color(0xffffc400)
                            )
                        }) {}
                }
                item {
                    AppCard(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
                        onClick = { /*TODO*/ },
                        appName = { Text("Dreka") },
                        time = { },
                        title = {
                            Text(
                                prayerTimes.value?.get(0)?.dreka.toString(),
                                color = Color(0xffffc400)
                            )
                        }) {}
                }
                item {
                    AppCard(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
                        onClick = { /*TODO*/ },
                        appName = { Text("Ikindia") },
                        time = { },
                        title = {
                            Text(
                                prayerTimes.value?.get(0)?.ikindia.toString(),
                                color = Color(0xffffc400)
                            )
                        }) {}
                }
                item {
                    AppCard(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
                        onClick = { /*TODO*/ },
                        appName = { Text("Akshami") },
                        time = { },
                        title = {
                            Text(
                                prayerTimes.value?.get(0)?.akshami.toString(),
                                color = Color(0xffffc400)
                            )
                        }) {}
                }
                item {
                    AppCard(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
                        onClick = { /*TODO*/ },
                        appName = { Text("Jacia") },
                        time = { },
                        title = {
                            Text(
                                prayerTimes.value?.get(0)?.jacia.toString(),
                                color = Color(0xffffc400)
                            )
                        }) {}
                }
            }
    }
}

