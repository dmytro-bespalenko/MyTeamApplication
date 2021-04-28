package com.example.myteamapplication.allteams

import com.google.gson.annotations.SerializedName


data class AllTeamModel(
    @SerializedName("metric")
    var metric: String? = null,
    @SerializedName("period")
    var period: String? = null,
    @SerializedName("programId")
    var programId: String? = null,
    @SerializedName("programName")
    var programName: String? = null,
    @SerializedName("results")
    var results: List<Result>? = null,
    @SerializedName("userTeamResult")
    var userTeamResult: UserTeamResult? = null
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