package com.example.myteamapplication.repositories

import com.example.myteamapplication.base.RestApi

class AllTeamRepository (var api: RestApi) {


    fun getAllTeamApi() = api.getAllTeams()

}