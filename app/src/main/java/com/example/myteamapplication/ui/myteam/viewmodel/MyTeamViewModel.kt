package com.example.myteamapplication.ui.myteam.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myteamapplication.TeamApplication
import com.example.myteamapplication.network.RestApi
import com.example.myteamapplication.room.repositories.RoomDistanceFilterRepository
import com.example.myteamapplication.ui.main.viewmodel.BasicViewModel
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("CheckResult")
class MyTeamViewModel(
    instance: TeamApplication,
    var distanceFilterRepository: RoomDistanceFilterRepository
) : BasicViewModel(instance) {

    override var api: RestApi = instance.api

    private val distanceFilters: MutableLiveData<List<String>> = MutableLiveData()
    private val timePeriodFilters: MutableLiveData<List<String>> = MutableLiveData()
    private val activeDistanceFilter: MutableLiveData<String> = MutableLiveData()
    private val activeTimePeriodFilter: MutableLiveData<String> = MutableLiveData()


    fun getDistanceFilters(): LiveData<List<String>> {
        return distanceFilters
    }

    fun getTimePeriodFilters(): LiveData<List<String>> {
        return timePeriodFilters
    }

    fun getActiveDistanceFilter(): LiveData<String> {
        return activeDistanceFilter
    }

    fun getActiveTimePeriodFilter(): LiveData<String> {
        return activeTimePeriodFilter
    }



    fun updateDistanceFilters() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val response = distanceFilterRepository.getDistanceFilters()
                    distanceFilters.postValue(response)
                } catch (exception: Exception) {
                    Log.d("TAG", "updateDistanceFilters: $exception")
                }
            }
        }

    }

    fun updateActiveDistanceFilter() {
        distanceFilterRepository.getActiveDistance()
            .subscribeOn(Schedulers.io())
            .subscribe({ ad -> activeDistanceFilter.postValue(ad) },
                { t -> Log.d("TAG", "updateActiveDistanceFilter: $t") }
            )
    }

    fun updateActiveTimePeriodFilter() {
        distanceFilterRepository.getActivePeriod()
            .subscribeOn(Schedulers.io())
            .subscribe({ ad -> activeTimePeriodFilter.postValue(ad) },
                { t -> Log.d("TAG", "updateActiveTimePeriodFilter: $t") }
            )

    }


    fun updateTimePeriodFilters() {
        distanceFilterRepository.getTimePeriodFilters()
            .subscribeOn(Schedulers.io())
            .subscribe({ ad -> timePeriodFilters.postValue(ad) },
                { t -> Log.d("TAG", "updateTimePeriodFilters: $t") }
            )
    }

    fun setActiveDistanceFilter(step: String?) {
        distanceFilterRepository.updateActiveDistance(step)

    }

    fun setActiveTimePeriodFilter(time: String) {
        distanceFilterRepository.updateActiveTimePeriod(time)
    }


}