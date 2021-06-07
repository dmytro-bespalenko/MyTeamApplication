package com.example.myteamapplication.room.repositories

import android.app.Application
import com.example.myteamapplication.repositories.DistanceFilterRepository
import com.example.myteamapplication.room.FilterDatabase
import com.example.myteamapplication.room.dao.DistanceDao
import com.example.myteamapplication.room.entity.EntityDistanceFilter
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
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

    override fun getActiveDistance(): Single<String> {

        return distanceDao.loadActiveDistance()
    }

    override fun getTimePeriodFilters(): Single<List<String>> {

        return distanceDao.loadTimePeriodFilters()
    }

    override suspend fun getDistanceFilters(): List<String> {
        return distanceDao.loadDistanceFilters()
    }

    override fun getActivePeriod(): Single<String> {

        return distanceDao.loadActivePeriod()
    }

    override fun updateActiveDistance(step: String?) {
        Executors.newSingleThreadExecutor().execute {
            distanceDao.updateActiveDistanceFilter(step)
        }

    }

    fun updateActiveTimePeriod(time: String) {

        Executors.newSingleThreadExecutor().execute {
            distanceDao.updateActiveTimePeriodFilter(time)
        }

    }
}