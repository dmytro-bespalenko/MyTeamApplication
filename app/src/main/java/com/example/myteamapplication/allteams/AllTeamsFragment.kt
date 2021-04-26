package com.example.myteamapplication.allteams

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myteamapplication.R
import com.example.myteamapplication.adapters.AllTeamRecyclerAdapter
import com.example.myteamapplication.base.BasicFragment
import com.example.myteamapplication.base.TeamApplication

class AllTeamsFragment : BasicFragment() {

    var allTeamsList: MutableList<AllTeamModel> = mutableListOf()

    private var allTeamViewModel: AllTeamsViewModel = AllTeamsViewModel(TeamApplication.instance)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        allTeamViewModel = ViewModelProvider(
            requireActivity(),
            AllTeamsFactory(requireActivity().application))
            .get(AllTeamsViewModel::class.java)


    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        allTeamViewModel.getTeams().observe(requireActivity(), { a ->
            allTeamsList.clear()
            allTeamsList.addAll(a)

        })

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView_all_team)
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())

        recyclerView.adapter = AllTeamRecyclerAdapter(allTeamsList)


    }

    override fun onResume() {
        super.onResume()
        allTeamViewModel.updateAllTeams()
        Log.d("TAG", "onResume: $this")
    }
}