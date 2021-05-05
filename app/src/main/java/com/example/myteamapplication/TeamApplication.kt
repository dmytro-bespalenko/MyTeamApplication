package com.example.myteamapplication

import android.app.Application
import com.example.myteamapplication.network.NetworkManager
import com.example.myteamapplication.network.RestApi

class TeamApplication : Application() {


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

    }

}