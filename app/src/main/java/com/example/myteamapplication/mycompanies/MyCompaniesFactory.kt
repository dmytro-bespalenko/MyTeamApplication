package com.example.myteamapplication.mycompanies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myteamapplication.allteams.AllTeamsViewModel
import com.example.myteamapplication.base.TeamApplication

class MyCompaniesFactory() : ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MyCompaniesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MyCompaniesViewModel(TeamApplication.instance) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }


}