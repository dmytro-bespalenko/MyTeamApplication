package com.example.myteamapplication.ui.myteam

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myteamapplication.databinding.FragmentMyTeamBinding
import com.example.myteamapplication.ui.main.fragment.BaseFragment
import com.example.myteamapplication.ui.main.fragment.REQUEST_DISTANCE_DIALOG
import com.example.myteamapplication.ui.main.fragment.REQUEST_TIME_PERIOD_DIALOG
import com.example.myteamapplication.ui.myteam.adapter.MyTeamAdapterData
import com.example.myteamapplication.ui.myteam.adapter.MyTeamRecyclerAdapter
import com.example.myteamapplication.ui.myteam.viewmodel.MyTeamViewModel
import java.util.*


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class MyTeamFragment : BaseFragment<MyTeamViewModel, FragmentMyTeamBinding>(),
    MyTeamRecyclerAdapter.OnItemClickListener {

    var activeDistanceFilter: MutableList<String> = mutableListOf()
    var activeTimePeriodFilter: MutableList<String> = mutableListOf()
    private var distanceFilterList: ArrayList<String> = ArrayList()
    private var timePeriodFiltersList: ArrayList<String> = ArrayList()


    override fun getViewModel(): Class<MyTeamViewModel> = MyTeamViewModel::class.java

    private var recyclerAdapter = MyTeamRecyclerAdapter(
        MyTeamAdapterData(activeDistanceFilter, activeTimePeriodFilter), this
    )


    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentMyTeamBinding.inflate(inflater, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewAllTeam.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = recyclerAdapter
        }

        viewModel
            .getActiveDistanceFilter()
            .observe(viewLifecycleOwner,
                { ad ->
                    activeDistanceFilter.clear()
                    activeDistanceFilter.add(ad)
                    recyclerAdapter.notifyItemChanged(activeDistanceFilter.indexOf(ad))
                }
            )


//        viewModel
//            .getActiveTimePeriodFilter()
//            .observe(viewLifecycleOwner,
//                { tp ->
//                    activeTimePeriodFilter.clear()
//                    activeTimePeriodFilter.add(tp)
//                    recyclerAdapter.notifyItemChanged(activeTimePeriodFilter.indexOf(tp))
//                }
//            )
//
//        viewModel
//            .getDistanceFilters()
//            .observe(viewLifecycleOwner,
//                { f ->
//                    distanceFilterList.clear()
//                    distanceFilterList.addAll(f)
//                }
//
//            )
//
//        viewModel
//            .getTimePeriodFilters()
//            .observe(viewLifecycleOwner,
//                { f ->
//                    timePeriodFiltersList.clear()
//                    timePeriodFiltersList.addAll(f)
//                }
//            )

//        getActiveDistance(activeDistanceFilter)

        getActiveTimePeriod(activeTimePeriodFilter)

        getDistance(distanceFilterList)

        getTimePeriod(timePeriodFiltersList)

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_DISTANCE_DIALOG -> {
                    activeDistanceFilter[0] = data?.getStringExtra("step").toString()
                    viewModel.setActiveDistanceFilter(activeDistanceFilter[0])
                    recyclerAdapter.notifyDataSetChanged()
                }
                REQUEST_TIME_PERIOD_DIALOG -> {
                    activeTimePeriodFilter[0] = data?.getStringExtra("time").toString()
                    viewModel.setActiveTimePeriodFilter(activeTimePeriodFilter[0])
                    recyclerAdapter.notifyDataSetChanged()
                }
            }
        }
    }


    override fun onItemDistanceClick() {
        onDistanceClick(distanceFilterList, this)
    }

    override fun onItemTimeFrameClick() {
        onTimeClick(timePeriodFiltersList, this)
    }

    override fun onResume() {
        super.onResume()
        viewModel.updateDistanceFilters()
        viewModel.updateTimePeriodFilters()
        viewModel.updateActiveDistanceFilter()
        viewModel.updateActiveTimePeriodFilter()

    }


}