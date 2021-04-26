package com.example.myteamapplication.base

import android.app.Application
import com.example.myteamapplication.mycompanies.MyCompaniesResponse
import io.reactivex.Single

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