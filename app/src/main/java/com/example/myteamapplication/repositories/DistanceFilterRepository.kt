package com.example.myteamapplication.repositories


interface DistanceFilterRepository {

    suspend fun getActivePeriod(): String
    suspend fun getActiveDistance(): String
    suspend fun getDistanceFilters(): List<String>
    suspend fun getTimePeriodFilters(): List<String>
    suspend fun updateActiveDistance(step: String?)
    suspend fun updateActiveTimePeriod(time: String)

}
