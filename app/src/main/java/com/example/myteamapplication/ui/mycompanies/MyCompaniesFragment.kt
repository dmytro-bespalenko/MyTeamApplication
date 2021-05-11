package com.example.myteamapplication.ui.mycompanies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myteamapplication.R
import com.example.myteamapplication.TeamApplication
import com.example.myteamapplication.ui.customview.CustomSelectActivityDialogClass
import com.example.myteamapplication.ui.customview.CustomTimeFrameDialogClass
import com.example.myteamapplication.ui.main.fragment.BasicFragment
import com.example.myteamapplication.ui.mycompanies.adapter.MyCompaniesRecyclerAdapter
import com.example.myteamapplication.ui.mycompanies.veiwmodel.MyCompaniesDisplayModel
import com.example.myteamapplication.ui.mycompanies.veiwmodel.MyCompaniesFactory
import com.example.myteamapplication.ui.mycompanies.veiwmodel.MyCompaniesViewModel

class MyCompaniesFragment : BasicFragment(), MyCompaniesRecyclerAdapter.OnItemClickListener {

    var myCompaniesList: MutableList<MyCompaniesDisplayModel> = mutableListOf()
    var recyclerAdapter = MyCompaniesRecyclerAdapter(myCompaniesList, this)


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

    override fun onItemActivityClick() {
        fragmentManager?.let {
            CustomSelectActivityDialogClass().show(it, "MyCustomFragment")
        }
    }

    override fun onItemTimeFrameClick() {
        fragmentManager?.let { CustomTimeFrameDialogClass().show(it, "MyCustomFragment") }
    }


    override fun onResume() {
        super.onResume()
        Log.d("TAG", "onResume: $this")
        myCompaniesViewModel.updateCompanies()
    }
}