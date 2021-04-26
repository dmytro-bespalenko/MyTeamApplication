package com.example.myteamapplication.allteams

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myteamapplication.base.RestApi
import com.example.myteamapplication.base.TeamApplication
import com.example.myteamapplication.viewmodel.BasicViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

@SuppressLint("CheckResult")

class AllTeamsViewModel(var instance: TeamApplication) : BasicViewModel() {
    var api: RestApi = instance.api


    private val allTeams: MutableLiveData<List<AllTeamModel>> =
        MutableLiveData<List<AllTeamModel>>()

    fun getTeams(): LiveData<List<AllTeamModel>> {
        return allTeams
    }


    fun updateAllTeams() {
        api.getAllTeams()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError(Consumer { Log.d("TAG", "updateAllTeams: ") })
            .subscribe { units -> allTeams.postValue(units) }

    }



}