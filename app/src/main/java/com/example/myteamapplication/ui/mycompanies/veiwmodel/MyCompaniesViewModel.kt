package com.example.myteamapplication.ui.mycompanies.veiwmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myteamapplication.TeamApplication
import com.example.myteamapplication.network.models.mycompanies.Result
import com.example.myteamapplication.room.repositories.RoomDistanceFilterRepository
import com.example.myteamapplication.ui.main.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("CheckResult")
class MyCompaniesViewModel(
    instance: TeamApplication, private
    var distanceFilterRepository: RoomDistanceFilterRepository
) : BaseViewModel(instance) {


    private val myCompanies: MutableLiveData<List<MyCompaniesDisplayModel>> =
        MutableLiveData<List<MyCompaniesDisplayModel>>()

    val highLightCompany: MutableLiveData<Result> = MutableLiveData<Result>()


    fun getCompanies(): LiveData<List<MyCompaniesDisplayModel>> {
        return myCompanies
    }


    fun highLightRank() {
        viewModelScope.launch(exceptionHandler + Dispatchers.IO) {
            api.getMyCompany().userResult.let {
                highLightCompany.postValue(it)
            }

        }
    }

    fun updateCompanies() {
        val list: MutableList<MyCompaniesDisplayModel> = mutableListOf()
        viewModelScope.launch(exceptionHandler + Dispatchers.IO) {
            api.getMyCompany().results.forEach {
                list.add(
                    MyCompaniesDisplayModel(
                        it.avatar,
                        it.average,
                        it.averageDouble,
                        it.displayName,
                        it.rank,
                        it.total,
                        it.totalDouble,
                        it.userId

                    )
                )
            }
            myCompanies.postValue(list)
        }

    }

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