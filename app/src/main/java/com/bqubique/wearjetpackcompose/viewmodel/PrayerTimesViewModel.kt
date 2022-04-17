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

const val TAG = "PrayerTimesViewModel"

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
                _prayerTimes.value?.get(0)?.sabahu?.substringBefore(":")?.toInt()!!,
                _prayerTimes.value?.get(0)?.lindjaDiellit?.substringBefore(":")?.toInt()!!,
                _prayerTimes.value?.get(0)?.dreka?.substringBefore(":")?.toInt()!!,
                _prayerTimes.value?.get(0)?.ikindia?.substringBefore(":")?.toInt()!!,
                _prayerTimes.value?.get(0)?.akshami?.substringBefore(":")?.toInt()!!,
                _prayerTimes.value?.get(0)?.jacia?.substringBefore(":")?.toInt()!!,
            )

            val prayerTimesMinutes = listOf(
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

            for(i in prayerTimesHours.indices){
                if(prayerTimesHours[i] == currentHour){
                    when(i){
                        0 -> {
                            if(currentMinute<=prayerTimesMinutes[i]){
                                Log.d("ViewModel","Sabahu")
                                _nextPrayer.value = 0
                            }else{
                                Log.d("ViewModel","real Lindja e Diellit")
                                _nextPrayer.value = 1
                            }
                        }
                        1 -> {
                            if(currentMinute<=prayerTimesMinutes[i]){
                                Log.d("ViewModel","Lindja e Diellit")
                                _nextPrayer.value = 1
                            }else{
                                Log.d("ViewModel","real Dreka")
                                _nextPrayer.value = 2
                            }
                        }
                        2 -> {
                            if(currentMinute<=prayerTimesMinutes[i]){
                                Log.d("ViewModel","Dreka")
                                _nextPrayer.value = 2
                            }else{
                                Log.d("ViewModel","real Ikindia")
                                _nextPrayer.value = 3
                            }
                        }
                        3 -> {
                            if(currentMinute<=prayerTimesMinutes[i]){
                                Log.d("ViewModel","Ikindia")
                                _nextPrayer.value = 3
                            }else{
                                Log.d("ViewModel","real Akshami")
                                _nextPrayer.value = 4
                            }
                        }
                        4 -> {
                            if(currentMinute<=prayerTimesMinutes[i]){
                                Log.d("ViewModel","real Akshami")
                                _nextPrayer.value = 4
                            }else{
                                Log.d("ViewModel","real Jacia")
                                _nextPrayer.value = 5
                            }
                        }
                        5 -> {
                            if(currentMinute<=prayerTimesMinutes[i]){
                                Log.d("ViewModel","real Jacia")
                                _nextPrayer.value = 5
                            }else{
                                Log.d("ViewModel","real Sabahu")
                                _nextPrayer.value = 6
                            }
                        }
                    }
                    return
                }else if(prayerTimesHours[i] > currentHour){
                    when(i){
                        0 ->{
                            Log.d("ViewModel","Sabahu")
                            _nextPrayer.value = 0
                        }
                        1 ->{
                            Log.d("ViewModel","Lindja e diellit")
                            _nextPrayer.value = 1
                        }
                        2 ->{
                            Log.d("ViewModel","Dreka")
                            _nextPrayer.value = 2
                        }
                        3 ->{
                            Log.d("ViewModel","Ikindia")
                            _nextPrayer.value = 3
                        }
                        4 ->{
                            Log.d("ViewModel","Akshami")
                            _nextPrayer.value = 4
                        }
                        5 ->{
                            Log.d("ViewModel","Jacia")
                            _nextPrayer.value = 5
                        }
                    }
                    return
                }
            }
        }
    }
}