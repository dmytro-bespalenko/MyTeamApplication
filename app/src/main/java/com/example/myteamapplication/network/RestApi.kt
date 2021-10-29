package com.example.myteamapplication.network

import com.example.myteamapplication.network.models.allteams.AllTeamModel
import com.example.myteamapplication.network.models.mycompanies.MyCompaniesModel
import com.example.myteamapplication.network.models.myteam.MyTeamModel
import io.reactivex.Single
import retrofit2.http.GET

interface RestApi {


    @GET("/v1/f05eca9d-e706-42d7-ad76-d626a5efd21a")
    suspend fun getMyCompany(): MyCompaniesModel

    @GET("/v1/21f1a59c-7db1-4e09-9846-b4e3fbcbd513")
    suspend fun getAllTeams(): AllTeamModel

    @GET("/v1/d1877370-b414-4ca0-8e7a-c55d8d6c4bac")
    fun getMyTeam(): Single<MyTeamModel>


}
