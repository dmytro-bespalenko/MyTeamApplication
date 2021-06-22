package com.example.myteamapplication.ui.allteams.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myteamapplication.TeamApplication
import com.example.myteamapplication.ui.main.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("CheckResult")

class AllTeamsViewModel(
    instance: TeamApplication
) : BaseViewModel(instance) {

    private val allTeams: MutableLiveData<List<AllTeamsDisplayModel>> =
        MutableLiveData<List<AllTeamsDisplayModel>>()

    fun getTeams(): LiveData<List<AllTeamsDisplayModel>> {
        return allTeams
    }

    fun updateAllTeams() {
        val list: MutableList<AllTeamsDisplayModel> = mutableListOf()
        viewModelScope.launch(exceptionHandler + Dispatchers.IO) {
            api.getAllTeams().results?.forEach {
                list.add(
                    AllTeamsDisplayModel(
                        it.average,
                        it.averageDouble,
                        it.displayName,
                        it.rank,
                        it.teamId
                    )
                )
            }
            allTeams.postValue(list)
        }


    }


}


