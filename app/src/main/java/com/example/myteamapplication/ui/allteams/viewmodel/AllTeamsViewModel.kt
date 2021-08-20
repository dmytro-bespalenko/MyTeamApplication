package com.example.myteamapplication.ui.allteams.viewmodel

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

class AllTeamsViewModel(
    instance: TeamApplication,
    distanceFilterRepository: RoomDistanceFilterRepository
) : BaseViewModel(instance, distanceFilterRepository) {

    private val rankTeam: MutableLiveData<AllTeamsDisplayModel> = MutableLiveData()
    private val highLightPosition: MutableLiveData<Int> = MutableLiveData()
    lateinit var list: MutableList<AllTeamsDisplayModel>
    private val allTeams: MutableLiveData<List<AllTeamsDisplayModel>> =
        MutableLiveData<List<AllTeamsDisplayModel>>()

    fun getTeams(): LiveData<List<AllTeamsDisplayModel>> {
        return allTeams
    }

    fun getRankTeam(): LiveData<AllTeamsDisplayModel> = rankTeam

    fun getHighLightPosition(): LiveData<Int> = highLightPosition

    private fun updateHighLightItem() {
        var allTeamsDisplayModel: AllTeamsDisplayModel
        viewModelScope.launch(exceptionHandler + Dispatchers.IO) {
            api.getAllTeams().userTeamResult.let {
                allTeamsDisplayModel = AllTeamsDisplayModel(
                    it.average,
                    it.averageDouble,
                    it.displayName,
                    it.rank,
                    it.teamId
                )
            }
            rankTeam.postValue(allTeamsDisplayModel)
            highLightPosition.postValue(list.indexOf(allTeamsDisplayModel))
        }
    }


    fun updateAllTeams() {
        list = mutableListOf()
        viewModelScope.launch(exceptionHandler + Dispatchers.IO) {

            api.getAllTeams().results.forEach {
                list.add(
                    AllTeamsDisplayModel(
                        it.average,
                        it.averageDouble,
                        it.displayName,
                        it.rank,
                        it.teamId
                    )
                )
                allTeams.postValue(list)
            }

            updateHighLightItem()
        }

    }

}


