package com.example.myteamapplication.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myteamapplication.room.dao.DistanceDao
import com.example.myteamapplication.room.entity.EntityDistanceFilter

@Database(entities = [EntityDistanceFilter::class], version = 1,  exportSchema = false)
@TypeConverters(FilterConverter::class)

abstract class FilterDatabase : RoomDatabase() {

    abstract val filterDao: DistanceDao



    companion object {

        private var INSTANCE: FilterDatabase? = null

        fun getInstance(context: Context): FilterDatabase {

            synchronized(this) {

                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FilterDatabase::class.java,
                        "filter_database"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance

                }

                return instance
            }
        }
    }

}