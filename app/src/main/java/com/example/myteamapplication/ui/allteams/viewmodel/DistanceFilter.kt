package com.example.myteamapplication.ui.allteams.viewmodel

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class DistanceFilter(


    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "_name") val firstName: String?,


    )
