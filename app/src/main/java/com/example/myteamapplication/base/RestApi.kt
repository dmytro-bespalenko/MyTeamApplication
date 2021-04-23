package com.example.myteamapplication.base

import com.example.myteamapplication.mycompanies.MyCompaniesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RestApi {


    @GET("/mocki.io/v1/5472d014-4ee8-4a52-a571-6d159beb1e0c/{id}")
    fun getMyCompany(@Path("id") mycompanyId: Int): Single<List<MyCompaniesResponse>>

    @GET("/mocki.io/v1/21f1a59c-7db1-4e09-9846-b4e3fbcbd513/{id}")
    fun getAllTeams(@Path("id") mycompanyId: Int): Single<List<MyCompaniesResponse>>

    @GET("/mocki.io/v1/d1877370-b414-4ca0-8e7a-c55d8d6c4bac/{id}")
    fun getMyTeam(@Path("id") mycompanyId: Int): Single<List<MyCompaniesResponse>>


}
