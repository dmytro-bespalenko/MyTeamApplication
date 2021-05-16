package com.example.myteamapplication.repositories

import com.example.myteamapplication.ui.allteams.viewmodel.DistanceFilter

interface DistanceFilterRepository {

    fun save(distanceFilter: DistanceFilter)

//    fun get(): DistanceFilter

    fun saveAllFilters(list: List<String>)


}
