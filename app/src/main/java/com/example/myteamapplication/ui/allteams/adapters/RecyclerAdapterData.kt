package com.example.myteamapplication.ui.allteams.adapters

import com.example.myteamapplication.network.models.allteams.UserTeamResult
import com.example.myteamapplication.ui.allteams.viewmodel.AllTeamsDisplayModel

data class RecyclerAdapterData(
    var allTeamsList: MutableList<AllTeamsDisplayModel>,
    var rank: List<UserTeamResult>
)
