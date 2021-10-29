package com.example.myteamapplication

import android.app.Application
import com.example.myteamapplication.di.*
import com.example.myteamapplication.network.NetworkManager
import com.example.myteamapplication.network.RestApi
import com.example.myteamapplication.room.FilterDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

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
        startKoin {
            androidContext(this@TeamApplication)
            modules(listOf(viewModelModule, apiModule, netModule, databaseModule, coroutineModule))

        }

    }


}