package com.example.myteamapplication.room.repositories

import android.app.Application
import com.example.myteamapplication.repositories.DistanceFilterRepository
import com.example.myteamapplication.room.FilterDatabase
import com.example.myteamapplication.room.dao.DistanceDao
import com.example.myteamapplication.room.entity.EntityDistanceFilter
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

class RoomDistanceFilterRepository(application: Application) : DistanceFilterRepository {


    private var distanceDao: DistanceDao


    companion object {
        @Volatile
        private var INSTANCE: RoomDistanceFilterRepository? = null

        fun getInstance(applicationContext: Application): RoomDistanceFilterRepository {
            return INSTANCE ?: RoomDistanceFilterRepository(applicationContext)
        }

    }

    init {
        val database: FilterDatabase = FilterDatabase.getInstance(application.applicationContext)
        distanceDao = database.filterDao

        GlobalScope.launch {


        }
        Executors.newSingleThreadExecutor().execute {
            if (distanceDao.selectAllUsers().isEmpty()) {
                distanceDao.insertAllFilters(
                    EntityDistanceFilter(
                        1,
                        "Step",
                        "Today",
                        mutableListOf("Today", "7 Days", "Month"),
                        mutableListOf("Step", "Km")
                    )
                ).subscribeOn(Schedulers.io())
                    .subscribe()
            }

        }


    }

    override suspend fun getActiveDistance(): String {

        return distanceDao.loadActiveDistance()
    }

    override suspend fun getTimePeriodFilters(): List<String> {

        return distanceDao.loadTimePeriodFilters()
    }

    override suspend fun getDistanceFilters(): List<String> {
        return distanceDao.loadDistanceFilters()
    }

    override suspend fun getActivePeriod(): String {

        return distanceDao.loadActivePeriod()
    }

    override suspend fun updateActiveDistance(step: String?) {
        distanceDao.updateActiveDistanceFilter(step)
    }

    override suspend fun updateActiveTimePeriod(time: String) {

        distanceDao.updateActiveTimePeriodFilter(time)


    }
}