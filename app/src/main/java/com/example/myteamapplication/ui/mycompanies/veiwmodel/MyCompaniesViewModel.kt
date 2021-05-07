package com.example.myteamapplication.ui.mycompanies.veiwmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myteamapplication.network.RestApi
import com.example.myteamapplication.TeamApplication
import com.example.myteamapplication.ui.main.viewmodel.BasicViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@SuppressLint("CheckResult")
class MyCompaniesViewModel(instance: TeamApplication) : BasicViewModel(instance) {

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