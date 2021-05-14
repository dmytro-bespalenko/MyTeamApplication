package com.example.myteamapplication.room.repositories

import android.app.Application
import com.example.myteamapplication.repositories.DistanceFilterRepository
import com.example.myteamapplication.room.FilterDatabase
import com.example.myteamapplication.room.dao.DistanceDao
import com.example.myteamapplication.room.entity.EntityDistanceFilter
import com.example.myteamapplication.ui.allteams.viewmodel.DistanceFilter
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
    }

    override fun save(distanceFilter: DistanceFilter) {

        Executors.newSingleThreadExecutor().execute {
            distanceDao.insertFilter(
                EntityDistanceFilter(
                    distanceFilter.id,
                    distanceFilter.name,
                    distanceFilter.isChecked
                )
            )
        }

    }


    override fun get(): DistanceFilter {
        TODO("Not yet implemented")

//        distanceDao.getFilter()
    }

    override fun saveAllFilters(list: MutableList<String>) {

    }
}