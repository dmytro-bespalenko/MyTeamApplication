package com.example.myteamapplication.ui.myteam.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myteamapplication.TeamApplication
import com.example.myteamapplication.room.repositories.RoomDistanceFilterRepository

class MyTeamFactory(private val repository: RoomDistanceFilterRepository) :
    ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MyTeamViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MyTeamViewModel(TeamApplication.instance, repository) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }


}