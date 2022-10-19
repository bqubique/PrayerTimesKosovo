package com.bqubique.takvimi_wearos_compose.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.runtime.Composable

import com.bqubique.takvimi_wearos_compose.view.home.HomeScreen
import com.bqubique.takvimi_wearos_compose.view.theme.WearJetpackComposeTheme
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
    WearJetpackComposeTheme {
        HomeScreen()
    }
}

