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

            for (i in prayerTimesHours.indices) {
                if (prayerTimesHours[i]!! > currentHour) {
                    when (i) {
                        0 -> {
                            //Imsak
                            _nextPrayer.value = 0
                            return
                        }
                        1 -> {
                            //Fajr
                            _nextPrayer.value = 1
                            return
                        }
                        2 -> {
                            //Sunrise
                            _nextPrayer.value = 2
                            return
                        }
                        3 -> {
                            //Dhuhr
                            _nextPrayer.value = 3
                            return
                        }
                        4 -> {
                            //Asr
                            _nextPrayer.value = 4
                            return
                        }
                        5 -> {
                            //Maghrib
                            _nextPrayer.value = 5
                            return
                        }
                        6 -> {
                            //Isha
                            _nextPrayer.value = 6
                            return
                        }
                    }
                } else if (prayerTimesHours[i]!! == currentHour) {
                    if (prayerTimesMinutes[i]!! <= currentMinute) {
                        when (i) {
                            0 -> {
                                //Imsak
                                _nextPrayer.value = 0
                                return
                            }
                            1 -> {
                                //Fajr
                                _nextPrayer.value = 1
                                return
                            }
                            2 -> {
                                //Sunrise
                                _nextPrayer.value = 2
                                return
                            }
                            3 -> {
                                //Dhuhr
                                _nextPrayer.value = 3
                                return
                            }
                            4 -> {
                                //Asr
                                _nextPrayer.value = 4
                                return
                            }
                            5 -> {
                                //Maghrib
                                _nextPrayer.value = 5
                                return
                            }
                            6 -> {
                                //Isha
                                _nextPrayer.value = 6
                                return
                            }
                        }
                    }
                } else {
                    //Imsak
                    _nextPrayer.value = 0
                }
            }
        }
    }
}