package com.bqubique.wearjetpackcompose.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bqubique.wearjetpackcompose.api.PrayerTimesApi
import com.bqubique.wearjetpackcompose.model.PrayerTimes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.*
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

const val TAG = "MainViewModel"

@HiltViewModel
class PrayerTimesViewModel @Inject constructor(
    var prayerTimesApi: PrayerTimesApi
) : ViewModel() {

    private val _prayerTimes = MutableLiveData<PrayerTimes>()
    val prayerTimes: LiveData<PrayerTimes> = _prayerTimes

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _nextPrayer = MutableLiveData(-1)
    val nextPrayer: LiveData<Int> = _nextPrayer

    init {
        _loading.postValue(true)
        getPrayerTimes()
    }

    private fun getPrayerTimes() {
        viewModelScope.launch {
            val response = prayerTimesApi.getRandomAyah()
            if (response.isSuccessful) {
                _prayerTimes.value = response.body()
                _loading.postValue(false)
                selectCurrentPrayer()
            }
        }
    }

    private fun selectCurrentPrayer() {
        if (_prayerTimes.value.isNullOrEmpty().not()) {
            Log.d(TAG, "selectCurrentPrayer: HELLO FROM")
            val prayerTimesHours = listOf(
                _prayerTimes.value?.get(0)?.imsaku?.substringBefore(":")?.toInt()!!,
                _prayerTimes.value?.get(0)?.sabahu?.substringBefore(":")?.toInt()!!,
                _prayerTimes.value?.get(0)?.lindjaDiellit?.substringBefore(":")?.toInt()!!,
                _prayerTimes.value?.get(0)?.dreka?.substringBefore(":")?.toInt()!!,
                _prayerTimes.value?.get(0)?.ikindia?.substringBefore(":")?.toInt()!!,
                _prayerTimes.value?.get(0)?.akshami?.substringBefore(":")?.toInt()!!,
                _prayerTimes.value?.get(0)?.jacia?.substringBefore(":")?.toInt()!!,
            )

            val prayerTimesMinutes = listOf(
                _prayerTimes.value?.get(0)?.imsaku?.substringAfter(":")?.toInt()!!,
                _prayerTimes.value?.get(0)?.sabahu?.substringAfter(":")?.toInt()!!,
                _prayerTimes.value?.get(0)?.lindjaDiellit?.substringAfter(":")?.toInt()!!,
                _prayerTimes.value?.get(0)?.dreka?.substringAfter(":")?.toInt()!!,
                _prayerTimes.value?.get(0)?.ikindia?.substringAfter(":")?.toInt()!!,
                _prayerTimes.value?.get(0)?.akshami?.substringAfter(":")?.toInt()!!,
                _prayerTimes.value?.get(0)?.jacia?.substringAfter(":")?.toInt()!!,
            )


            val rightNow = Calendar.getInstance()
            val currentHour = rightNow.get(Calendar.HOUR_OF_DAY)
            val currentMinute = rightNow.get(Calendar.HOUR_OF_DAY)

            val time: LocalTime = LocalTime.of(currentHour, currentMinute)

            for (prayerTime in prayerTimesHours.indices) {

                if (prayerTimesHours[prayerTime] > currentHour) {
                    when (prayerTime) {
                        0 -> {
                            Log.d("PrayerTimesList", "Imsak")
                            _nextPrayer.value = 0
                            return
                        }
                        1 -> {
                            Log.d("PrayerTimesList", "Morning")
                            _nextPrayer.value = 1
                            return
                        }
                        2 -> {
                            Log.d("PrayerTimesList", "Sunrise")
                            _nextPrayer.value = 2
                            return
                        }
                        3 -> {
                            Log.d(TAG, "selectCurrentPrayer: ${prayerTimesHours[3]} ${prayerTimesMinutes[3]}")
                            Log.d(TAG, "selectCurrentPrayer: --->>> ${rightNow.get(Calendar.HOUR_OF_DAY)} ${rightNow.get(Calendar.MINUTE)}")
                            var date = Date(2022, 4, 4, prayerTimesHours[3], prayerTimesMinutes[3])
                            var date2 = Date(
                                2022,
                                4,
                                4,
                                rightNow.get(Calendar.HOUR_OF_DAY),
                                rightNow.get(Calendar.MINUTE)
                            )



                            val diff: Long = date.time.minus(date2.time)
                            val seconds = diff / 1000
                            val minutes = seconds / 60
                            val hours = minutes / 60
                            Log.d(TAG, "selectCurrentPrayer: $hours :: $minutes")
                            _nextPrayer.value = 3
                            return
                        }
                        4 -> {
                            Log.d("PrayerTimesList", "Asr")
                            _nextPrayer.value = 4
                            return
                        }
                        5 -> {
                            Log.d("PrayerTimesList", "Maghrib")
                            _nextPrayer.value = 5
                            return
                        }
                        6 -> {
                            Log.d("PrayerTimesList", "Isha")
                            _nextPrayer.value = 6
                            return
                        }
                    }
                } else if (prayerTimesHours[prayerTime] == currentHour) {
                    if (prayerTimesMinutes[prayerTime]!! <= currentMinute) {
                        when (prayerTime) {
                            0 -> {
                                Log.d("PrayerTimesList", "Imsak")
                                return
                            }
                            1 -> {
                                Log.d("PrayerTimesList", "Fajr")
                                return
                            }
                            2 -> {
                                Log.d("PrayerTimesList", "Sunrise")
                                return
                            }
                            3 -> {
                                Log.d("PrayerTimesList", "Dhuhr")

                                return
                            }
                            4 -> {
                                Log.d("PrayerTimesList", "Asr")

                                return
                            }
                            5 -> {
                                Log.d("PrayerTimesList", "Maghrib")
                                return
                            }
                            6 -> {
                                Log.d("PrayerTimesList", "Isha")
                                return
                            }
                        }
                    }
                }
            }

        }
    }
}