package com.example.myteamapplication.network.models.mycompanies

import com.google.gson.annotations.SerializedName


data class MyCompaniesModel(
    @SerializedName("metric")
    var metric: String,
    @SerializedName("period")
    var period: String,
    @SerializedName("programId")
    var programId: String,
    @SerializedName("programName")
    var programName: String,
    @SerializedName("results")
    var results: List<Result>,
    @SerializedName("userResult")
    var userResult: Any
)

data class Result(
    @SerializedName("avatar")
    var avatar: String,
    @SerializedName("average")
    var average: Int,
    @SerializedName("average_double")
    var averageDouble: Int,
    @SerializedName("displayName")
    var displayName: String,
    @SerializedName("rank")
    var rank: Int,
    @SerializedName("total")
    var total: Int,
    @SerializedName("total_double")
    var totalDouble: Int,
    @SerializedName("userId")
    var userId: String
)