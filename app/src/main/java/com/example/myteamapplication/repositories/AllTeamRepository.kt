package com.example.myteamapplication.repositories

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.example.myteamapplication.allteams.AllTeamModel
import com.example.myteamapplication.base.NetworkManager
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

object AllTeamRepository {

    val serviceSetterGetter = MutableLiveData<List<AllTeamModel>>()

    @SuppressLint("CheckResult")
    fun getAllTeamApi() {

        val call = NetworkManager.getRestApi()

//        call.getAllTeams()
//            .subscribeOn(Schedulers.io())
//            .subscribe()

    }

}