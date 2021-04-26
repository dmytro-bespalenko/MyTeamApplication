package com.example.myteamapplication.allteams

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myteamapplication.base.TeamApplication

class AllTeamsFactory(val app: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AllTeamsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AllTeamsViewModel(TeamApplication.instance) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}
