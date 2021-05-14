package com.example.myteamapplication.ui.mycompanies.veiwmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myteamapplication.TeamApplication
import com.example.myteamapplication.network.RestApi
import com.example.myteamapplication.room.repositories.RoomDistanceFilterRepository
import com.example.myteamapplication.ui.allteams.viewmodel.DistanceFilter
import com.example.myteamapplication.ui.main.viewmodel.BasicViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@SuppressLint("CheckResult")
class MyCompaniesViewModel(
    instance: TeamApplication,
    var distanceFilterRepository: RoomDistanceFilterRepository
) : BasicViewModel(instance) {

    override var api: RestApi = instance.api

    private val distanceFilter: MutableLiveData<DistanceFilter> = MutableLiveData()


    private val myCompanies: MutableLiveData<List<MyCompaniesDisplayModel>> =
        MutableLiveData<List<MyCompaniesDisplayModel>>()

    fun getCompanies(): LiveData<List<MyCompaniesDisplayModel>> {

        return myCompanies
    }

    fun getDistanceFilers() {
        TODO("Not yet implemented")
    }

    fun onDistanceFilterSelected(distance: DistanceFilter) {
        distanceFilterRepository.save(distance)
        distanceFilter.postValue(distanceFilter.value)

    }

    fun updateFilters() {
        distanceFilter.postValue(distanceFilterRepository.get())
    }

    fun updateCompanies() {
        api.getMyCompany()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { all ->
                    myCompanies.postValue(all.results.map { it ->
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



}