package com.example.myteamapplication.mycompanies

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myteamapplication.base.RestApi
import com.example.myteamapplication.base.TeamApplication
import com.example.myteamapplication.repositories.MyCompaniesRepository
import com.example.myteamapplication.viewmodel.BasicViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@SuppressLint("CheckResult")
class MyCompaniesViewModel(instance: TeamApplication) : BasicViewModel(instance) {

    var api: RestApi = instance.api

    private val myCompaniesRepository: MyCompaniesRepository = MyCompaniesRepository(api)

    private val myCompanies: MutableLiveData<List<Result>> = MutableLiveData<List<Result>>()

    fun getCompanies(): LiveData<List<Result>> {

        return myCompanies
    }


    fun updateCompanies() {
        myCompaniesRepository
            .getMyCompaniesApi()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { all -> myCompanies.postValue(all.results) },
                { t -> Log.d("TAG", "updateCompanies: $t") }
            )
    }

}