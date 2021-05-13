package com.example.myteamapplication.room.repositories

import com.example.myteamapplication.repositories.DistanceFilterRepository
import com.example.myteamapplication.room.dao.DistanceDao
import com.example.myteamapplication.room.entity.EntityDistanceFilter
import com.example.myteamapplication.ui.allteams.viewmodel.DistanceFilter

class RoomDistanceFilterRepository(var distanceDao: DistanceDao) : DistanceFilterRepository {


    override fun save(distanceFilter: DistanceFilter) {

        distanceDao.insertFilter(EntityDistanceFilter(distanceFilter.id, distanceFilter.name))
    }

    override fun get(): DistanceFilter {
        TODO("Not yet implemented")
    }
}