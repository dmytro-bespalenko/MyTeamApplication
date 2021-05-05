package com.example.myteamapplication.ui.mycompanies.veiwmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myteamapplication.TeamApplication

class MyCompaniesFactory() : ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MyCompaniesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MyCompaniesViewModel(TeamApplication.instance) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }


}