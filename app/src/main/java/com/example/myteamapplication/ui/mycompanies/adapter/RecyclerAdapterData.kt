package com.example.myteamapplication.ui.mycompanies.adapter

import com.example.myteamapplication.network.models.mycompanies.Result
import com.example.myteamapplication.ui.mycompanies.veiwmodel.MyCompaniesDisplayModel

data class RecyclerAdapterData(
    var myCompaniesDisplayModel: List<MyCompaniesDisplayModel> = arrayListOf(),
    var activeDistanceFilter: List<String>,
    var activeTimePeriodFilter: List<String>,
    var rankCompany: List<Result>
    )


