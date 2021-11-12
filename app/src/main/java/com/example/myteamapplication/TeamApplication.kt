package com.example.myteamapplication

import com.example.myteamapplication.di.DaggerAppComponent
import com.example.myteamapplication.network.NetworkManager
import com.example.myteamapplication.network.RestApi
import com.example.myteamapplication.room.FilterDatabase
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class TeamApplication : DaggerApplication() {


   private lateinit var database: FilterDatabase
    private val applicationInjector = DaggerAppComponent.builder().application(this).build()


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
//        startKoin {
//            androidContext(this@TeamApplication)
//            modules(listOf(viewModelModule, apiModule, netModule, databaseModule, coroutineModule))
//
//        }

    }
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = applicationInjector


}