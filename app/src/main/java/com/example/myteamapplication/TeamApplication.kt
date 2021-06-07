package com.example.myteamapplication

import android.app.Application
import com.example.myteamapplication.network.NetworkManager
import com.example.myteamapplication.network.RestApi
import com.example.myteamapplication.room.FilterDatabase

class TeamApplication : Application() {


   private lateinit var database: FilterDatabase


    companion object {
        lateinit var instance: TeamApplication
            private set
    }


    lateinit var api: RestApi
        private set


    override fun onCreate() {
        super.onCreate()

        instance = this
        api = NetworkManager.getRestApi()

        database = FilterDatabase.getInstance(instance)


    }


}