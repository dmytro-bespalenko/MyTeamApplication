package com.example.myteamapplication.ui.allteams

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myteamapplication.R
import com.example.myteamapplication.ui.allteams.adapters.AllTeamRecyclerAdapter
import com.example.myteamapplication.ui.allteams.viewmodel.AllTeamsDisplayModel
import com.example.myteamapplication.ui.allteams.viewmodel.AllTeamsViewModel
import com.example.myteamapplication.ui.main.fragment.BasicFragment

class AllTeamsFragment : BasicFragment<AllTeamsViewModel>() {

    override fun getViewModel(): Class<AllTeamsViewModel> = AllTeamsViewModel::class.java
    var allTeamsList: MutableList<AllTeamsDisplayModel> = mutableListOf()
    var recyclerAdapter = AllTeamRecyclerAdapter(allTeamsList)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_all_teams, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView_all_team)
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.adapter = recyclerAdapter

        viewModel.getTeams().observe(requireActivity(), { a ->
            allTeamsList.clear()
            allTeamsList.addAll(a)
            recyclerAdapter.notifyDataSetChanged()
        })


    }

    override fun onResume() {
        super.onResume()
        viewModel.updateAllTeams()
        Log.d("TAG", "onResume: $this")
    }


}

