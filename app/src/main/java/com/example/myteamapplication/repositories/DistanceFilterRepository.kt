package com.example.myteamapplication.repositories

import io.reactivex.Single

interface DistanceFilterRepository {

    fun getActivePeriod(): Single<String>
    fun getActiveDistance(): Single<String>
    suspend fun getDistanceFilters(): List<String>
    fun getTimePeriodFilters(): Single<List<String>>
    fun updateActiveDistance(step: String?)

}
