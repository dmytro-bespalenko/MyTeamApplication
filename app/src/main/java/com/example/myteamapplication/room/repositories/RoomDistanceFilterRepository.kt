package com.example.myteamapplication.room.repositories

import android.app.Application
import com.example.myteamapplication.repositories.DistanceFilterRepository
import com.example.myteamapplication.room.FilterDatabase
import com.example.myteamapplication.room.dao.DistanceDao
import com.example.myteamapplication.room.entity.EntityDistanceFilter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RoomDistanceFilterRepository(application: Application) : DistanceFilterRepository {

    private var distanceDao: DistanceDao
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    companion object {
        @Volatile
        var INSTANCE: RoomDistanceFilterRepository? = null

        fun getInstance(applicationContext: Application): RoomDistanceFilterRepository {
            return INSTANCE ?: RoomDistanceFilterRepository(applicationContext)
        }

    }

    init {
        val database: FilterDatabase = FilterDatabase.getInstance(application.applicationContext)
        distanceDao = database.filterDao

        coroutineScope.launch {
            if (distanceDao.selectAllUsers().isEmpty()) {
                distanceDao.insertAllFilters(
                    EntityDistanceFilter(
                        1,
                        "Step",
                        "Today",
                        listOf("Today", "7 Days", "Month"),
                        listOf("Step", "Km")
                    )
                )
            }
        }
    }

    override suspend fun getActiveDistance() = distanceDao.loadActiveDistance()

    override suspend fun getTimePeriodFilters() = distanceDao.loadTimePeriodFilters()

    override suspend fun getDistanceFilters() = distanceDao.loadDistanceFilters()

    override suspend fun getActivePeriod() = distanceDao.loadActivePeriod()

    override suspend fun updateActiveDistance(step: String?) {
        distanceDao.updateActiveDistanceFilter(step)
    }

    override suspend fun updateActiveTimePeriod(time: String) {
        distanceDao.updateActiveTimePeriodFilter(time)
    }


}