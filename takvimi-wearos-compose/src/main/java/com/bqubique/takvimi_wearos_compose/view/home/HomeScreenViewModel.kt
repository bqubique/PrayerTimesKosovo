package com.bqubique.takvimi_wearos_compose.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bqubique.takvimi_wearos_compose.api.PrayerTimesApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val prayerTimesApi: PrayerTimesApi
) : ViewModel() {
    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> = _loading

    private val _prayerTimesList = MutableLiveData<List<String?>>()
    val listOfPrayerTimes: LiveData<List<String?>> = _prayerTimesList

    init {
        getPrayerTimes()
    }

    private fun getPrayerTimes() {
        _loading.value = true
        viewModelScope.launch {
            val response = prayerTimesApi.getPrayerTimes()
            if (response.isSuccessful) {
                val body = response.body()
                val listOfPrayers = listOf(
                    body?.imsaku,
                    body?.lDiellit,
                    body?.dreka,
                    body?.ikindia,
                    body?.akshami,
                    body?.jacia,
                )
                _prayerTimesList.value = listOfPrayers
                _loading.value = false
            }
        }
    }
}