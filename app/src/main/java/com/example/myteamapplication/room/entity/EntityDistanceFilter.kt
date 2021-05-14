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

    @ColumnInfo(name = "name")
    val name: String,


    @ColumnInfo(name = "checked")
    val isChecked: Boolean,


    @TypeConverters(FilterConverter::class)
    var filters: List<String>? = null

)


