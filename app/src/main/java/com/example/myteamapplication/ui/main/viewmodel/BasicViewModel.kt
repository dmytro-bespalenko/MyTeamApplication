package com.example.myteamapplication.ui.main.viewmodel

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myteamapplication.TeamApplication
import com.example.myteamapplication.network.RestApi

abstract class BasicViewModel(app: TeamApplication) : AndroidViewModel(app) {

    open var api: RestApi = app.api

    val distanceFilters: MutableLiveData<List<String>> = MutableLiveData()
    val timePeriodFilters: MutableLiveData<List<String>> = MutableLiveData()
    val activeDistanceFilter: MutableLiveData<String> = MutableLiveData()
    val activeTimePeriodFilter: MutableLiveData<String> = MutableLiveData()


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

}
