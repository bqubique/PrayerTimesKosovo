package com.bqubique.wearjetpackcompose.view

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.ViewConfiguration
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.runtime.Composable
import androidx.core.view.InputDeviceCompat
import androidx.core.view.MotionEventCompat
import androidx.core.view.ViewConfigurationCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material.ScalingLazyListState
import androidx.wear.compose.material.rememberScalingLazyListState
import com.bqubique.quran_randomayah.theme.WearAppTheme
import com.bqubique.wearjetpackcompose.viewmodel.PrayerTimesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    lateinit var scalingLazyListState: ScalingLazyListState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            scalingLazyListState = rememberScalingLazyListState()
            WearApp(scalingLazyListState = scalingLazyListState)
        }
    }

    override fun onGenericMotionEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_SCROLL && event.isFromSource(InputDeviceCompat.SOURCE_ROTARY_ENCODER)) {
            runBlocking {
                val delta = -event.getAxisValue(MotionEventCompat.AXIS_SCROLL) *
                        ViewConfigurationCompat.getScaledVerticalScrollFactor(
                            ViewConfiguration.get(baseContext), baseContext
                        )
                if (delta < 127) {
                    scalingLazyListState.scrollBy(-100f)
                } else {
                    scalingLazyListState.scrollBy(100f)
                }
            }
        }
        return super.onGenericMotionEvent(event)
    }
}

@Composable
fun WearApp(
    scalingLazyListState: ScalingLazyListState
) {
    val viewModel: PrayerTimesViewModel = hiltViewModel()

    WearAppTheme {
        PrayerTimesList(
            prayerTimesViewModel = viewModel,
            scalingLazyListState = scalingLazyListState
        )
    }
}