package com.example.myteamapplication.base

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetworkManager {


    private const val BASE_URL = "https://mocki.io"

    private val okHttpClient = OkHttpClient()
        .newBuilder()
        .build()


    private fun retrofitService(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(createGSon()))
            .client(okHttpClient)
            .build()

    }

    fun getRestApi(): RestApi {

        return retrofitService().create(RestApi::class.java)
    }

    private fun createGSon(): Gson {
        return GsonBuilder()
            .create()
    }
}