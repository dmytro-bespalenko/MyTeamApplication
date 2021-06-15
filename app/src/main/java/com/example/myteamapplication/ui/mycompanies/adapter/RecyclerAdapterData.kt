package com.example.myteamapplication.ui.mycompanies.adapter

import com.example.myteamapplication.ui.mycompanies.veiwmodel.MyCompaniesDisplayModel

data class RecyclerAdapterData(
    var myCompaniesDisplayModel: List<MyCompaniesDisplayModel> = arrayListOf(),
    var activeDistanceFilter: List<String>,
    var activeTimePeriodFilter: List<String>,
    )


