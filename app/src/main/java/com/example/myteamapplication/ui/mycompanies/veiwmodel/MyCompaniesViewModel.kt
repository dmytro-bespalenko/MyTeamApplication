package com.example.myteamapplication.ui.mycompanies.veiwmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myteamapplication.network.RestApi
import com.example.myteamapplication.TeamApplication
import com.example.myteamapplication.repositories.MyCompaniesRepository
import com.example.myteamapplication.ui.main.viewmodel.BasicViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@SuppressLint("CheckResult")
class MyCompaniesViewModel(instance: TeamApplication) : BasicViewModel(instance) {

    var api: RestApi = instance.api

    private val myCompaniesRepository: MyCompaniesRepository = MyCompaniesRepository(api)

    private val myCompanies: MutableLiveData<List<MyCompaniesDisplayModel>> = MutableLiveData<List<MyCompaniesDisplayModel>>()

    fun getCompanies(): LiveData<List<MyCompaniesDisplayModel>> {

        return myCompanies
    }


    fun updateCompanies() {
        myCompaniesRepository
            .getMyCompaniesApi()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { all -> myCompanies.postValue(all.results.map {
                    TODO("Map Network model to Display model")
                }) },
                { t -> Log.d("TAG", "updateCompanies: $t") }
            )
    }

}