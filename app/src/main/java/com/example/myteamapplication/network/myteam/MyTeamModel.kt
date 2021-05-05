package com.example.myteamapplication.network.myteam
import com.google.gson.annotations.SerializedName


data class MyTeamModel(
    @SerializedName("metric")
    var metric: String,
    @SerializedName("period")
    var period: String,
    @SerializedName("programId")
    var programId: String,
    @SerializedName("programName")
    var programName: String,
    @SerializedName("results")
    var results: List<Any>,
    @SerializedName("teamId")
    var teamId: String,
    @SerializedName("teamName")
    var teamName: String,
    @SerializedName("userResult")
    var userResult: Any
)