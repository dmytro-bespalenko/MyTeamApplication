package com.example.myteamapplication.ui.allteams.viewmodel


data class DistanceFilter(

    var id: Int,
    var activeDistance: String,
    var activePeriod: String,
    var filtersPeriod: MutableList<String>,
    val filtersDistance: MutableList<String>


)
