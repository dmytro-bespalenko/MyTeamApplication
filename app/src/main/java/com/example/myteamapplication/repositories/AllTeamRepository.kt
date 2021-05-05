package com.example.myteamapplication.repositories

import com.example.myteamapplication.network.RestApi

class AllTeamRepository (var api: RestApi) {


    fun getAllTeamApi() = api.getAllTeams()

}