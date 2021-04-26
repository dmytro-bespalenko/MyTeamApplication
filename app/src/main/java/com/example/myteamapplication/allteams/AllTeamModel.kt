package com.example.myteamapplication.allteams
import com.google.gson.annotations.SerializedName


data class AllTeamModel(
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
    @SerializedName("userTeamResult")
    var userTeamResult: UserTeamResult
)

data class Result(
    @SerializedName("average")
    var average: Int,
    @SerializedName("average_double")
    var averageDouble: Int,
    @SerializedName("displayName")
    var displayName: String,
    @SerializedName("rank")
    var rank: Int,
    @SerializedName("teamId")
    var teamId: String
)

data class UserTeamResult(
    @SerializedName("average")
    var average: Int,
    @SerializedName("average_double")
    var averageDouble: Int,
    @SerializedName("displayName")
    var displayName: String,
    @SerializedName("rank")
    var rank: Int,
    @SerializedName("teamId")
    var teamId: String
)