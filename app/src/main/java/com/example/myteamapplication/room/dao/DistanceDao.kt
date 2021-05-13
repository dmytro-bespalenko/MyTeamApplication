package com.example.myteamapplication.room.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.myteamapplication.room.entity.EntityDistanceFilter

interface DistanceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFilter(entityDistanceFilter: EntityDistanceFilter)



}