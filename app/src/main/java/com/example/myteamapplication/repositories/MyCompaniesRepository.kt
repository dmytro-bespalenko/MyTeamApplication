package com.example.myteamapplication.repositories

import com.example.myteamapplication.network.RestApi

class MyCompaniesRepository(var api: RestApi) {

    fun getMyCompaniesApi() = api.getMyCompany()

}
