package com.bqubique.wearjetpackcompose.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material.CircularProgressIndicator
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.Text
import com.bqubique.quran_randomayah.theme.WearAppTheme
import com.bqubique.wearjetpackcompose.R
import com.bqubique.wearjetpackcompose.viewmodel.PrayerTimesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WearApp()
        }
    }
}

@Composable
fun WearApp() {
    val viewModel: PrayerTimesViewModel = hiltViewModel()

    WearAppTheme {
        PrayerTimesList(prayerTimesViewModel = viewModel)
    }
}