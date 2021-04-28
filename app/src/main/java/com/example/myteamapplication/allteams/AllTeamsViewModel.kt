package com.example.myteamapplication.allteams

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myteamapplication.base.RestApi
import com.example.myteamapplication.base.TeamApplication
import com.example.myteamapplication.viewmodel.BasicViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@SuppressLint("CheckResult")

class AllTeamsViewModel(instance: TeamApplication) : BasicViewModel() {

    var api: RestApi = instance.api

    private val allTeams: MutableLiveData<AllTeamModel> =
        MutableLiveData<AllTeamModel>()

    fun getTeams(): LiveData<AllTeamModel> {
        return allTeams
    }


    fun updateAllTeams() {

        api.getAllTeams()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { all -> allTeams.postValue(all) },
                { t -> Log.d("TAG", "updateAllTeams: +$t") })

    }


}

