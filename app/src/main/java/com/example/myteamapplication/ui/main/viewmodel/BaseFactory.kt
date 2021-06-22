package com.example.myteamapplication.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myteamapplication.TeamApplication
import com.example.myteamapplication.room.repositories.RoomDistanceFilterRepository
import com.example.myteamapplication.ui.allteams.viewmodel.AllTeamsViewModel
import com.example.myteamapplication.ui.mycompanies.veiwmodel.MyCompaniesViewModel
import com.example.myteamapplication.ui.myteam.viewmodel.MyTeamViewModel

@Suppress("UNCHECKED_CAST")
class BaseFactory(private val repository: RoomDistanceFilterRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return when {

            modelClass.isAssignableFrom(MyCompaniesViewModel::class.java) ->
                MyCompaniesViewModel(TeamApplication.instance, repository) as T

            modelClass.isAssignableFrom(MyTeamViewModel::class.java) ->
                MyTeamViewModel(TeamApplication.instance, repository) as T

            modelClass.isAssignableFrom(AllTeamsViewModel::class.java) ->
                AllTeamsViewModel(TeamApplication.instance) as T


            else -> throw IllegalArgumentException("Unable to construct viewmodel")
        }


    }


}