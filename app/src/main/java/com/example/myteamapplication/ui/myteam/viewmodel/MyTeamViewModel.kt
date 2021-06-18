package com.example.myteamapplication.ui.myteam.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.viewModelScope
import com.example.myteamapplication.TeamApplication
import com.example.myteamapplication.network.RestApi
import com.example.myteamapplication.room.repositories.RoomDistanceFilterRepository
import com.example.myteamapplication.ui.main.viewmodel.BasicViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@SuppressLint("CheckResult")
class MyTeamViewModel(
    instance: TeamApplication,
    var distanceFilterRepository: RoomDistanceFilterRepository
) : BasicViewModel(instance) {

    override var api: RestApi = instance.api


    fun updateDistanceFilters() {

        viewModelScope.launch(exceptionHandler + Dispatchers.IO) {
            val response = async { distanceFilterRepository.getDistanceFilters() }
            distanceFilters.postValue(response.await())
        }
    }

    fun updateActiveDistanceFilter() {

        viewModelScope.launch(exceptionHandler + Dispatchers.IO) {
            activeDistanceFilter.postValue(distanceFilterRepository.getActiveDistance())
        }

    }

    fun updateActiveTimePeriodFilter() {

        viewModelScope.launch(exceptionHandler + Dispatchers.IO) {
            activeTimePeriodFilter.postValue(distanceFilterRepository.getActivePeriod())
        }
    }


    fun updateTimePeriodFilters() {

        viewModelScope.launch(exceptionHandler + Dispatchers.IO) {
            timePeriodFilters.postValue(distanceFilterRepository.getTimePeriodFilters())
        }
    }

    fun setActiveDistanceFilter(step: String?) {

        viewModelScope.launch(exceptionHandler + Dispatchers.IO) {
            distanceFilterRepository.updateActiveDistance(step)
        }
    }

    fun setActiveTimePeriodFilter(time: String) {

        viewModelScope.launch(exceptionHandler + Dispatchers.IO) {
            distanceFilterRepository.updateActiveTimePeriod(time)
        }
    }
}