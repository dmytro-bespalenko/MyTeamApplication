package com.example.myteamapplication.ui.allteams.adapters

import com.example.myteamapplication.ui.allteams.viewmodel.AllTeamsDisplayModel
import java.util.*

data class RecyclerAdapterData(
    var allTeamsList: MutableList<AllTeamsDisplayModel>,
    var rank: ArrayList<AllTeamsDisplayModel>,
    var positionItem: ArrayList<Int>,
    var activeDistanceFilter: MutableList<String>,
    var activeTimePeriodFilter: MutableList<String>
)
