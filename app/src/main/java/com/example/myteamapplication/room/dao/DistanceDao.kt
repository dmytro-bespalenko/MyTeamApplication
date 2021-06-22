package com.example.myteamapplication.room.dao

import androidx.room.*
import com.example.myteamapplication.room.entity.EntityDistanceFilter


@Dao
interface DistanceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllFilters(entityDistanceFilter: EntityDistanceFilter)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAllFilters(entityDistanceFilter: EntityDistanceFilter)

    @Query("SELECT * FROM entitydistancefilter")
    suspend fun selectAllUsers(): List<EntityDistanceFilter>

    @Query("SELECT filtersDistance FROM entitydistancefilter")
    suspend fun loadDistanceFilters(): List<String>

    @Query("SELECT filtersPeriod FROM entitydistancefilter")
    suspend fun loadTimePeriodFilters(): List<String>


    @Query("SELECT activeDistance FROM entitydistancefilter")
    suspend fun loadActiveDistance(): String


    @Query("SELECT activePeriod FROM entitydistancefilter")
    suspend fun loadActivePeriod(): String

    @Query("UPDATE entitydistancefilter SET activeDistance=:step WHERE id = 1")
    suspend fun updateActiveDistanceFilter(step: String?)


    @Query("UPDATE entitydistancefilter SET activePeriod=:time WHERE id = 1")
    suspend fun updateActiveTimePeriodFilter(time: String)
}