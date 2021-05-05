package com.example.myteamapplication.network

import com.example.myteamapplication.network.models.AllTeamModel
import com.example.myteamapplication.network.models.mycompanies.MyCompaniesModel
import com.example.myteamapplication.network.myteam.MyTeamModel
import io.reactivex.Single
import retrofit2.http.GET

interface RestApi {


    @GET("/v1/5472d014-4ee8-4a52-a571-6d159beb1e0c")
    fun getMyCompany(): Single<MyCompaniesModel>

    @GET("/v1/21f1a59c-7db1-4e09-9846-b4e3fbcbd513")
    fun getAllTeams(): Single<AllTeamModel>

    @GET("/v1/d1877370-b414-4ca0-8e7a-c55d8d6c4bac")
    fun getMyTeam(): Single<MyTeamModel>


}
