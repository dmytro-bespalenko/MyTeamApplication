package com.example.myteamapplication.ui.main.fragment

import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myteamapplication.TeamApplication
import com.example.myteamapplication.room.repositories.RoomDistanceFilterRepository
import com.example.myteamapplication.ui.mycompanies.veiwmodel.MyCompaniesFactory

abstract class BasicFragment<VM : AndroidViewModel> : Fragment() {

    val viewModel: VM by lazy {
        val factory =
            MyCompaniesFactory(RoomDistanceFilterRepository.getInstance(TeamApplication.instance))

        ViewModelProvider(this, factory).get(getViewModel())
    }

    abstract fun getViewModel(): Class<VM>


}