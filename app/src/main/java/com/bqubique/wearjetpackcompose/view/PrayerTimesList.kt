package com.bqubique.wearjetpackcompose.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.CircularProgressIndicator
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.Text
import com.bqubique.wearjetpackcompose.R
import com.bqubique.wearjetpackcompose.viewmodel.PrayerTimesViewModel
import java.util.*

@Composable
fun PrayerTimesList(prayerTimesViewModel: PrayerTimesViewModel) {
    val formFactor = stringResource(id = R.string.form_factor)
    val prayerTimes = prayerTimesViewModel.prayerTimes.observeAsState()
    val loading = prayerTimesViewModel.loading.observeAsState()

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
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp, bottom = 10.dp, start = 20.dp, end = 20.dp)
        ) {
            item {
                Text("${prayerTimes.value?.get(0)?.imsaku}")
            }
            item {
                Text("${prayerTimes.value?.get(0)?.sabahu}")
            }
            item {
                Text("${prayerTimes.value?.get(0)?.lindjaDiellit}")
            }
            item {
                Text("${prayerTimes.value?.get(0)?.dreka}")
            }
            item {
                Text("${prayerTimes.value?.get(0)?.ikindia}")
            }
            item {
                Text("${prayerTimes.value?.get(0)?.akshami}")
            }
            item {
                Text("${prayerTimes.value?.get(0)?.jacia}")
            }
        }

}

@Composable
fun selectCurrentPrayer(viewModel: PrayerTimesViewModel){
    val prayerHour = viewModel.prayerTimes.value?.get(0)?.sabahu
    val rightNow = Calendar.getInstance()
    val currentHour = rightNow.get(Calendar.HOUR_OF_DAY)
    val currentMinute = rightNow.get(Calendar.HOUR_OF_DAY)
    if()
}