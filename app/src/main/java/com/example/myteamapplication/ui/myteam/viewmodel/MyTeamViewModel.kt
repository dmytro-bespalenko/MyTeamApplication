package com.example.myteamapplication.ui.myteam.viewmodel

import android.annotation.SuppressLint
import com.example.myteamapplication.TeamApplication
import com.example.myteamapplication.network.RestApi
import com.example.myteamapplication.room.repositories.RoomDistanceFilterRepository
import com.example.myteamapplication.ui.main.viewmodel.BaseViewModel

@SuppressLint("CheckResult")
class MyTeamViewModel(
    instance: TeamApplication,
    distanceFilterRepository: RoomDistanceFilterRepository
) : BaseViewModel(instance, distanceFilterRepository) {

    override var api: RestApi = instance.api


}