package com.bqubique.takvimi_wearos_compose.view

import android.os.Bundle
import android.view.MotionEvent
import android.view.ViewConfiguration
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.gestures.scrollBy

import androidx.compose.runtime.Composable
import androidx.core.view.InputDeviceCompat
import androidx.core.view.MotionEventCompat
import androidx.core.view.ViewConfigurationCompat
import androidx.wear.compose.material.ScalingLazyListState
import androidx.wear.compose.material.rememberScalingLazyListState

import com.bqubique.takvimi_wearos_compose.view.home.HomeScreen
import com.bqubique.takvimi_wearos_compose.view.theme.WearJetpackComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking

lateinit var scalingLazyListState: ScalingLazyListState

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            scalingLazyListState = rememberScalingLazyListState()
            WearApp(scalingLazyListState = scalingLazyListState)
        }
    }

    override fun onGenericMotionEvent(event: MotionEvent?): Boolean {
        if (event!!.action == MotionEvent.ACTION_SCROLL && event.isFromSource(InputDeviceCompat.SOURCE_ROTARY_ENCODER)) {
            // Don't forget the negation here
            val delta =
                -event.getAxisValue(MotionEventCompat.AXIS_SCROLL) * ViewConfigurationCompat.getScaledVerticalScrollFactor(
                    ViewConfiguration.get(baseContext), baseContext
                )
            // Swap these axes to scroll horizontally instead
            runBlocking {
                coroutineScope {
                    scalingLazyListState.scrollBy(delta)
                }
            }
            return true
        } else {
            return false
        }
    }
}

@Composable
fun WearApp(scalingLazyListState: ScalingLazyListState) {
    WearJetpackComposeTheme {
        HomeScreen(scalingLazyListState = scalingLazyListState)
    }
}

