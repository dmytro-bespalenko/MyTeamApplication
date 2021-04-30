package com.example.myteamapplication.mycompanies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myteamapplication.R
import com.example.myteamapplication.adapters.MyCompaniesRecyclerAdapter
import com.example.myteamapplication.base.BasicFragment
import com.example.myteamapplication.base.TeamApplication

class MyCompaniesFragment : BasicFragment() {

    var myCompaniesList: MutableList<Result> = mutableListOf()
    var recyclerAdapter = MyCompaniesRecyclerAdapter(myCompaniesList)

    private var myCompaniesViewModel: MyCompaniesViewModel =
        MyCompaniesViewModel(TeamApplication.instance)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myCompaniesViewModel = ViewModelProvider(
            requireActivity(), MyCompaniesFactory()
        )
            .get(MyCompaniesViewModel::class.java)

    }

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

        myCompaniesViewModel
            .getCompanies()
            .observe(this,
                { c ->
                    myCompaniesList.clear()
                    myCompaniesList.addAll(c)
                    recyclerAdapter.notifyDataSetChanged()

                })

    }

    override fun onResume() {
        super.onResume()
        Log.d("TAG", "onResume: $this")
        myCompaniesViewModel.updateCompanies()
    }
}