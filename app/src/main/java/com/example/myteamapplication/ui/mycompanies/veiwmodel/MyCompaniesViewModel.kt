package com.example.myteamapplication.ui.mycompanies.veiwmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myteamapplication.TeamApplication
import com.example.myteamapplication.network.RestApi
import com.example.myteamapplication.room.repositories.RoomDistanceFilterRepository
import com.example.myteamapplication.ui.main.viewmodel.BasicViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("CheckResult")
class MyCompaniesViewModel(
    instance: TeamApplication, private
    var distanceFilterRepository: RoomDistanceFilterRepository
) : BasicViewModel(instance) {

    override var api: RestApi = instance.api


    private val myCompanies: MutableLiveData<List<MyCompaniesDisplayModel>> =
        MutableLiveData<List<MyCompaniesDisplayModel>>()

    fun getCompanies(): LiveData<List<MyCompaniesDisplayModel>> {
        return myCompanies
    }


    fun updateCompanies() {
        api.getMyCompany()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { all ->
                    myCompanies.postValue(all.results.map {
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
                    })
                },
                { t -> Log.d("TAG", "updateCompanies: $t") }
            )


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