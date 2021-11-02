package com.example.myteamapplication.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.myteamapplication.network.RestApi
import com.example.myteamapplication.room.FilterDatabase
import com.example.myteamapplication.room.dao.DistanceDao
import com.example.myteamapplication.ui.main.viewmodel.BaseViewModel
import com.example.myteamapplication.ui.mycompanies.veiwmodel.MyCompaniesViewModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

const val BASE_URL = "https://mocki.io"

val viewModelModule = module {

    viewModel {
        BaseViewModel(get(), get())
        MyCompaniesViewModel(get(), distanceFilterRepository = get())
    }

}

val coroutineModule = module {

    single {
        Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
            Log.d("TAG", ": $throwable")
        }
    }
}

val apiModule = module {

    fun provideUserApi(retrofit: Retrofit): RestApi {

        return retrofit.create(RestApi::class.java)

    }
    single { provideUserApi(get()) }

}

val netModule = module {

    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .build()
    }

    fun retrofitService(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    }

    single { provideHttpClient() }
    single { retrofitService(get()) }

}

val databaseModule = module {

    fun provideDatabase(context: Context): FilterDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            FilterDatabase::class.java,
            "filter_database"
        ).fallbackToDestructiveMigration()
            .build()
    }

    fun provideDao(database: FilterDatabase): DistanceDao {
        return database.filterDao
    }

    single { provideDatabase(androidContext()) }
    single { provideDao(get()) }

}