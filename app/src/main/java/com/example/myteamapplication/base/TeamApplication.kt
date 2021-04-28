package com.example.myteamapplication.base

import android.app.Application

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