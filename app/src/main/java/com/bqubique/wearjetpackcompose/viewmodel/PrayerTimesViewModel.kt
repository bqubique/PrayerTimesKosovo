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

    init {
        _loading.postValue(true)
        getPrayerTimes()
    }

    private fun getPrayerTimes() {
        viewModelScope.launch {
            val response = prayerTimesApi.getRandomAyah()
            if(response.isSuccessful){
                _prayerTimes.postValue(response.body())
                _loading.postValue(false)
            }
        }
    }
}