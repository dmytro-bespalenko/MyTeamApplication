package com.example.myteamapplication.base

import com.example.myteamapplication.allteams.AllTeamModel
import com.example.myteamapplication.mycompanies.MyCompaniesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RestApi {


    @GET("/mocki.io/v1/{id}")
    fun getMyCompany(@Path("id") myCompanyId: Int): Single<List<MyCompaniesResponse>>

    @GET("/21f1a59c-7db1-4e09-9846-b4e3fbcbd513")
    fun getAllTeams(): Single<List<AllTeamModel>>

    @GET("/mocki.io/v1/{id}")
    fun getMyTeam(@Path("id") myTeamId: Int): Single<List<MyCompaniesResponse>>


}
