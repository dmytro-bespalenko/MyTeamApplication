package com.example.myteamapplication.ui.main.viewmodel

import androidx.lifecycle.AndroidViewModel
import com.example.myteamapplication.TeamApplication
import com.example.myteamapplication.network.RestApi

abstract class BasicViewModel(app: TeamApplication) : AndroidViewModel(app) {
    protected var api: RestApi = app.api


}
