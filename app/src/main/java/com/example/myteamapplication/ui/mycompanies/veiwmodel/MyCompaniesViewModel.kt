package com.example.myteamapplication.ui.mycompanies.veiwmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myteamapplication.TeamApplication
import com.example.myteamapplication.room.repositories.RoomDistanceFilterRepository
import com.example.myteamapplication.ui.main.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("CheckResult")
class MyCompaniesViewModel(
    instance: TeamApplication, distanceFilterRepository: RoomDistanceFilterRepository
) : BaseViewModel(instance, distanceFilterRepository) {

    private var highLightItemPosition: MutableLiveData<Int> = MutableLiveData()

    lateinit var list: MutableList<MyCompaniesDisplayModel>

    private val myCompanies: MutableLiveData<List<MyCompaniesDisplayModel>> =
        MutableLiveData<List<MyCompaniesDisplayModel>>()

    private val highLightCompany: MutableLiveData<MyCompaniesDisplayModel> =
        MutableLiveData<MyCompaniesDisplayModel>()

    fun getHighLightItem(): MutableLiveData<MyCompaniesDisplayModel> = highLightCompany

    fun getCompanies(): MutableLiveData<List<MyCompaniesDisplayModel>> = myCompanies

    fun getHighLightPosition(): LiveData<Int> = highLightItemPosition

    private fun updateHighLightItem() {
        var myCompaniesDisplayModel: MyCompaniesDisplayModel
        viewModelScope.launch(exceptionHandler + Dispatchers.IO) {
            api.getMyCompany().userResult.let {
                myCompaniesDisplayModel = MyCompaniesDisplayModel(
                    it.avatar,
                    it.average,
                    it.averageDouble,
                    it.displayName,
                    it.rank,
                    it.total,
                    it.totalDouble,
                    it.userId
                )
            }
            highLightCompany.postValue(myCompaniesDisplayModel)
            highLightItemPosition.postValue(list.indexOf(myCompaniesDisplayModel) + 1)
        }
    }


    fun updateCompanies() {
        list = mutableListOf()
        viewModelScope.launch(exceptionHandler + Dispatchers.IO) {
            api.getMyCompany().results.forEach {
                list.add(
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
                )
            }
            myCompanies.postValue(list)
            updateHighLightItem()

        }


    }


}