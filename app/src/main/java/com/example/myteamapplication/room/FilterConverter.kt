package com.example.myteamapplication.room

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.util.stream.Collectors


class FilterConverter {

    @RequiresApi(Build.VERSION_CODES.N)
    @TypeConverter
    fun fromFilters(filters: List<String?>): String? {
        return filters.stream().collect(Collectors.joining(","))
    }

    @TypeConverter
    fun toFilters(data: String): String {
        return mutableListOf(data.split(",")).toString()
    }
}