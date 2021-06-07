package com.example.myteamapplication.ui.mycompanies

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myteamapplication.R
import com.example.myteamapplication.TeamApplication
import com.example.myteamapplication.room.repositories.RoomDistanceFilterRepository
import com.example.myteamapplication.ui.customview.SelectDistanceDialogFragment
import com.example.myteamapplication.ui.customview.SelectTimePeriodDialogFragment
import com.example.myteamapplication.ui.main.fragment.BasicFragment
import com.example.myteamapplication.ui.mycompanies.adapter.MyCompaniesRecyclerAdapter
import com.example.myteamapplication.ui.mycompanies.adapter.RecyclerAdapterData
import com.example.myteamapplication.ui.mycompanies.veiwmodel.MyCompaniesDisplayModel
import com.example.myteamapplication.ui.mycompanies.veiwmodel.MyCompaniesFactory
import com.example.myteamapplication.ui.mycompanies.veiwmodel.MyCompaniesViewModel

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class MyCompaniesFragment : BasicFragment(), MyCompaniesRecyclerAdapter.OnItemClickListener {

    private var myCompaniesList: MutableList<MyCompaniesDisplayModel> = mutableListOf()

    private var distanceFilterList: ArrayList<String> = ArrayList()
    private var timePeriodFiltersList: ArrayList<String> = ArrayList()
    var activeDistanceFilter: MutableList<String> = mutableListOf()
    var activeTimePeriodFilter: MutableList<String> = mutableListOf()

    private val REQUEST_DISTANCE_DIALOG = 0
    private val REQUEST_TIME_PERIOD_DIALOG = 1

    var recyclerAdapter =
        MyCompaniesRecyclerAdapter(
            RecyclerAdapterData(myCompaniesList, activeDistanceFilter, activeTimePeriodFilter),
            this
        )

    private lateinit var myCompaniesViewModel: MyCompaniesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        myCompaniesViewModel = ViewModelProvider(
            requireActivity(),
            MyCompaniesFactory(RoomDistanceFilterRepository.getInstance(TeamApplication.instance))
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
            .observe(viewLifecycleOwner,
                { c ->
                    myCompaniesList.clear()
                    myCompaniesList.addAll(c)
                    recyclerAdapter.notifyDataSetChanged()

                })

        myCompaniesViewModel
            .getActiveDistanceFilter()
            .observe(viewLifecycleOwner,
                { ad ->
                    activeDistanceFilter.clear()
                    activeDistanceFilter.add(ad)
                    recyclerAdapter.notifyDataSetChanged()
                }
            )

        myCompaniesViewModel
            .getActiveTimePeriodFilter()
            .observe(viewLifecycleOwner,
                { tp ->
                    activeTimePeriodFilter.clear()
                    activeTimePeriodFilter.add(tp)
                    recyclerAdapter.notifyDataSetChanged()
                }
            )

        myCompaniesViewModel
            .getDistanceFilters()
            .observe(viewLifecycleOwner,
                { f ->
                    distanceFilterList.clear()
                    distanceFilterList.addAll(f)
                }

            )

        myCompaniesViewModel
            .getTimePeriodFilters()
            .observe(viewLifecycleOwner,
                { f ->
                    timePeriodFiltersList.clear()
                    timePeriodFiltersList.addAll(f)
                }
            )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_DISTANCE_DIALOG -> {
                    activeDistanceFilter[0] = data?.getStringExtra("step").toString()
                    myCompaniesViewModel.setActiveDistanceFilter(activeDistanceFilter[0])
                    recyclerAdapter.notifyDataSetChanged()
                }
                REQUEST_TIME_PERIOD_DIALOG -> {
                    activeTimePeriodFilter[0] = data?.getStringExtra("time").toString()
                    myCompaniesViewModel.setActiveTimePeriodFilter(activeTimePeriodFilter[0])
                    recyclerAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    companion object {
        fun newSelectDistanceDialogFragmentInstance(
            fragment: Fragment,
            filters: List<String>
        ): SelectDistanceDialogFragment {
            val args = Bundle()
            args.putStringArrayList("list", ArrayList(filters));
            val fragmentSD = SelectDistanceDialogFragment()
            fragmentSD.arguments = args
            fragmentSD.setTargetFragment(fragment, 0)
            return fragmentSD
        }

        fun newSelectTimePeriodDialogFragmentInstance(
            fragment: Fragment,
            filters: List<String>,
        ): SelectTimePeriodDialogFragment {
            val args = Bundle()
            args.putStringArrayList("list", ArrayList(filters));
            val fragmentTP = SelectTimePeriodDialogFragment()
            fragmentTP.arguments = args
            fragmentTP.setTargetFragment(fragment, 1)
            return fragmentTP
        }
    }

    override fun onItemDistanceClick() {
        fragmentManager?.let {
            newSelectDistanceDialogFragmentInstance(this, distanceFilterList)
                .show(
                    it,
                    "MyCustomFragment"
                )
        }

    }

    override fun onItemTimeFrameClick() {
        fragmentManager?.let {
            newSelectTimePeriodDialogFragmentInstance(this, timePeriodFiltersList)
                .show(
                    it,
                    "MyCustomFragment"
                )
        }
    }


    override fun onResume() {
        super.onResume()
        myCompaniesViewModel.updateCompanies()
        myCompaniesViewModel.updateDistanceFilters()
        myCompaniesViewModel.updateTimePeriodFilters()
        myCompaniesViewModel.updateActiveDistanceFilter()
        myCompaniesViewModel.updateActiveTimePeriodFilter()

    }


}

