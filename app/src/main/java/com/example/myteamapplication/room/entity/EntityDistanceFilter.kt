package com.example.myteamapplication.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.myteamapplication.room.FilterConverter

@Entity
data class EntityDistanceFilter(

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "activeDistance")
    val activeDistance: String,

    @ColumnInfo(name = "activePeriod")
    val activePeriod: String,


    @ColumnInfo(name = "filtersPeriod")
    @TypeConverters(FilterConverter::class)
    var filtersPeriodName: List<String>,


    @ColumnInfo(name = "filtersDistance")
    @TypeConverters(FilterConverter::class)
    var filtersDistanceName: List<String>


)


