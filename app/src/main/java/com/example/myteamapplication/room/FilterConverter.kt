package com.example.myteamapplication.room

import androidx.room.TypeConverter
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


open class FilterConverter {

    @TypeConverter
    open fun fromString(value: String?): List<String?>? {
        val listType: Type = object : TypeToken<ArrayList<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    open fun fromArrayList(list: List<String?>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }
}
