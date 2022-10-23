package com.bqubique.takvimi_wearos_compose.view

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.wear.compose.material.ScalingLazyListState
import androidx.wear.compose.material.rememberScalingLazyListState
import com.bqubique.takvimi_wearos_compose.services.AlarmReceiver

import com.bqubique.takvimi_wearos_compose.view.home.HomeScreen
import com.bqubique.takvimi_wearos_compose.view.theme.WearJetpackComposeTheme
import dagger.hilt.android.AndroidEntryPoint

lateinit var scalingLazyListState: ScalingLazyListState
const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    var alarmManager: AlarmManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        alarmManager = applicationContext.getSystemService(Context.ALARM_SERVICE) as? AlarmManager?
        val pendingIntentMap = createIntentMap()

        setContent {
            scalingLazyListState = rememberScalingLazyListState()
            WearJetpackComposeTheme {
                HomeScreen(
                    scalingLazyListState = scalingLazyListState,
                    alarmManager = alarmManager!!,
                    pendingIntentMap = pendingIntentMap
                )
            }
        }
    }

    private fun createIntentMap(): Map<String,PendingIntent>{
        val map = mutableMapOf<String, PendingIntent>()

        val sunriseAlarmIntent = Intent(applicationContext, AlarmReceiver::class.java, ).let { intent ->
            PendingIntent.getBroadcast(
                applicationContext,
                0,
                intent.putExtra("prayerTimeId", 0),
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        }
        map["sunrise"] = sunriseAlarmIntent

        val preSunriseAlarmIntent = Intent(applicationContext, AlarmReceiver::class.java, ).let { intent ->
            PendingIntent.getBroadcast(
                applicationContext,
                1,
                intent.putExtra("prayerTimeId", 1),
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        }

        map["preSunrise"] = preSunriseAlarmIntent

        val dhuhrAlarmIntent = Intent(applicationContext, AlarmReceiver::class.java, ).let { intent ->
            PendingIntent.getBroadcast(
                applicationContext,
                2,
                intent.putExtra("prayerTimeId", 2),
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        }
        map["dhuhr"] = dhuhrAlarmIntent

        val preDhuhrAlarmIntent = Intent(applicationContext, AlarmReceiver::class.java, ).let { intent ->
            PendingIntent.getBroadcast(
                applicationContext,
                3,
                intent.putExtra("prayerTimeId", 3),
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        }
        map["preDhuhr"] = preDhuhrAlarmIntent

        val asrAlarmIntent = Intent(applicationContext, AlarmReceiver::class.java, ).let { intent ->
            PendingIntent.getBroadcast(
                applicationContext,
                4,
                intent.putExtra("prayerTimeId", 4),
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        }
        map["asr"] = asrAlarmIntent

        val preAsrAlarmIntent = Intent(applicationContext, AlarmReceiver::class.java, ).let { intent ->
            PendingIntent.getBroadcast(
                applicationContext,
                5,
                intent.putExtra("prayerTimeId", 5),
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        }
        map["preAsr"] = preAsrAlarmIntent

        val maghribAlarmIntent = Intent(applicationContext, AlarmReceiver::class.java, ).let { intent ->
            PendingIntent.getBroadcast(
                applicationContext,
                6,
                intent.putExtra("prayerTimeId", 6),
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        }
        map["maghrib"] = maghribAlarmIntent

        val preMaghribAlarmIntent = Intent(applicationContext, AlarmReceiver::class.java, ).let { intent ->
            PendingIntent.getBroadcast(
                applicationContext,
                7,
                intent.putExtra("prayerTimeId", 7),
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        }
        map["preMaghrib"] = preMaghribAlarmIntent

        val ishaAlarmIntent = Intent(applicationContext, AlarmReceiver::class.java, ).let { intent ->
            PendingIntent.getBroadcast(
                applicationContext,
                8,
                intent.putExtra("prayerTimeId", 8),
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        }
        map["isha"] = ishaAlarmIntent

        val preIshaAlarmIntent = Intent(applicationContext, AlarmReceiver::class.java, ).let { intent ->
            PendingIntent.getBroadcast(
                applicationContext,
                9,
                intent.putExtra("prayerTimeId", 9),
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        }
        map["preIsha"] = preIshaAlarmIntent

        return map
    }
}


