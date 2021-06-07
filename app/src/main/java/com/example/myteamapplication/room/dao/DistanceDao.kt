package com.example.myteamapplication.room.dao

import androidx.room.*
import com.example.myteamapplication.room.entity.EntityDistanceFilter
import io.reactivex.Completable
import io.reactivex.Single


@Dao
interface DistanceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllFilters(entityDistanceFilter: EntityDistanceFilter): Completable

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateAllFilters(entityDistanceFilter: EntityDistanceFilter): Completable

    @Query("SELECT * FROM entitydistancefilter")
    fun selectAllUsers(): List<EntityDistanceFilter>

    @Query("SELECT filtersDistance FROM entitydistancefilter")
    fun loadDistanceFilters(): List<String>

    @Query("SELECT filtersPeriod FROM entitydistancefilter")
    fun loadTimePeriodFilters(): Single<List<String>>


    @Query("SELECT activeDistance FROM entitydistancefilter")
    fun loadActiveDistance(): Single<String>


    @Query("SELECT activePeriod FROM entitydistancefilter")
    fun loadActivePeriod(): Single<String>

    @Query("UPDATE entitydistancefilter SET activeDistance=:step WHERE id = 1")
    fun updateActiveDistanceFilter(step: String?)


    @Query("UPDATE entitydistancefilter SET activePeriod=:time WHERE id = 1")
    fun updateActiveTimePeriodFilter(time: String)
}