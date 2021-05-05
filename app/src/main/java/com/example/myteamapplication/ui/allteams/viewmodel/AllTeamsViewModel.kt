package com.example.myteamapplication.ui.allteams.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myteamapplication.TeamApplication
import com.example.myteamapplication.ui.main.viewmodel.BasicViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@SuppressLint("CheckResult")

class AllTeamsViewModel(instance: TeamApplication) : BasicViewModel(instance) {

    private val allTeams: MutableLiveData<List<AllTeamsDisplayModel>> =
        MutableLiveData<List<AllTeamsDisplayModel>>()

    private val distanceFilter:MutableLiveData<DistanceFilter> = MutableLiveData()

    fun getTeams(): LiveData<List<AllTeamsDisplayModel>> {
        return allTeams
    }

    fun onDistanceFilterSelected(distanceFilter: DistanceFilter) {
        distanceFilterRepository.save(distanceFilter)
        distanceFilter.post(distanceFilter)
    }

    fun updateFilters(){
        distanceFilter.postValue(distanceFilterRepository.get())
    }

    fun updateAllTeams() {
        api.getAllTeams()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { all -> allTeams.postValue(all.results?.map { TODO("Map Network model to Display model") }) },
                { t -> Log.d("TAG", "updateAllTeams: +$t") })

    }


}

