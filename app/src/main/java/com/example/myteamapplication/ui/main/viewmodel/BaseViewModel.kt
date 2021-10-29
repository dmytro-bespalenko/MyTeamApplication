package com.example.myteamapplication.ui.main.viewmodel

import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myteamapplication.TeamApplication
import com.example.myteamapplication.network.RestApi
import com.example.myteamapplication.room.repositories.RoomDistanceFilterRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class BaseViewModel(
    app: TeamApplication, private
    var distanceFilterRepository: RoomDistanceFilterRepository
) : AndroidViewModel(app) {

    open var api: RestApi = app.api

    private val distanceFilters: MutableLiveData<List<String>> = MutableLiveData()
    private val timePeriodFilters: MutableLiveData<List<String>> = MutableLiveData()
    val activeDistanceFilter: MutableLiveData<String> = MutableLiveData()
    val activeTimePeriodFilter: MutableLiveData<String> = MutableLiveData()


    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.d("TAG", ": $throwable")
    }


    fun getDistanceFilters(): LiveData<List<String>> = distanceFilters

    fun getTimePeriodFilters(): LiveData<List<String>> = timePeriodFilters

    fun getActiveDistanceFilter(): LiveData<String> = activeDistanceFilter

    fun getActiveTimePeriodFilter(): LiveData<String> = activeTimePeriodFilter


    fun updateDistanceFilters() {
        viewModelScope.launch(exceptionHandler + Dispatchers.IO) {
            val response = distanceFilterRepository.getDistanceFilters()
            distanceFilters.postValue(response)
        }

    }

    fun updateTimePeriodFilters() {
        viewModelScope.launch(exceptionHandler + Dispatchers.IO) {
            timePeriodFilters.postValue(distanceFilterRepository.getTimePeriodFilters())
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

