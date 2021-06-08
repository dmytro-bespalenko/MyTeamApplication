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
import kotlinx.coroutines.withContext

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