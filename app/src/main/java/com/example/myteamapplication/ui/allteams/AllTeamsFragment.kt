package com.example.myteamapplication.ui.allteams

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myteamapplication.R
import com.example.myteamapplication.databinding.FragmentAllTeamsBinding
import com.example.myteamapplication.ui.allteams.adapters.AllTeamRecyclerAdapter
import com.example.myteamapplication.ui.allteams.adapters.RecyclerAdapterData
import com.example.myteamapplication.ui.allteams.viewmodel.AllTeamsDisplayModel
import com.example.myteamapplication.ui.allteams.viewmodel.AllTeamsViewModel
import com.example.myteamapplication.ui.main.fragment.BaseFragment
import com.example.myteamapplication.ui.main.fragment.REQUEST_DISTANCE_DIALOG
import com.example.myteamapplication.ui.main.fragment.REQUEST_TIME_PERIOD_DIALOG
import java.util.*


@SuppressLint("NotifyDataSetChanged")
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)

class AllTeamsFragment
    : BaseFragment<AllTeamsViewModel, FragmentAllTeamsBinding>(),
    AllTeamRecyclerAdapter.OnItemClickListener {

    override fun getViewModel(): Class<AllTeamsViewModel> = AllTeamsViewModel::class.java

    private var allTeamsList: MutableList<AllTeamsDisplayModel> = mutableListOf()

    private var distanceFilterList: ArrayList<String> = ArrayList()
    private var timePeriodFiltersList: ArrayList<String> = ArrayList()
    private var activeDistanceFilter: MutableList<String> = mutableListOf()
    private var activeTimePeriodFilter: MutableList<String> = mutableListOf()

    private var rankTeam: ArrayList<AllTeamsDisplayModel> = ArrayList()
    private var positionItem: ArrayList<Int> = ArrayList()
    private var recyclerAdapter: AllTeamRecyclerAdapter? = null
    private var animationDuration: Long = 0
    private var currentLastPosition = 0
    private var currentFirstPosition = 0
    private var highLightPosition = 0
    private var isAnimationShowing = false


    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentAllTeamsBinding.inflate(inflater, container, false)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllTeamsBinding.inflate(layoutInflater)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        animationDuration =
            resources.getInteger(android.R.integer.config_longAnimTime).toLong()

//        Log.d("TAG", "onViewCreated: $viewModel allTeams")

        recyclerAdapter =
            AllTeamRecyclerAdapter(
                RecyclerAdapterData(
                    allTeamsList,
                    rankTeam,
                    positionItem,
                    activeDistanceFilter,
                    activeTimePeriodFilter
                ),
                this
            )
        binding.recyclerViewAllTeam.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = recyclerAdapter

        }

        viewModel.getRankTeam().observe(
            viewLifecycleOwner,
            {
                rankTeam.clear()
                rankTeam.add(it)
                updateCollapsingLayout(it)
            }

        )

        viewModel.getTeams().observe(requireActivity(), { a ->
            allTeamsList.clear()
            allTeamsList.addAll(a)
            recyclerAdapter!!.notifyDataSetChanged()
        })

        viewModel.getHighLightPosition().observe(viewLifecycleOwner,
            {
                positionItem.clear()
                highLightPosition = it
                positionItem.add(it)

                if (!isOnScreen()) {
                    viewVisibleAnimator(binding.collapsingAllTeamLayout)
                } else {
                    viewGoneAnimator(binding.collapsingAllTeamLayout)
                }
            }
        )
        getActiveDistance(activeDistanceFilter)

        getActiveTimePeriod(activeTimePeriodFilter)

        getDistance(distanceFilterList)

        getTimePeriod(timePeriodFiltersList)


        binding.recyclerViewAllTeam.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager = binding.recyclerViewAllTeam.layoutManager as LinearLayoutManager

                currentLastPosition = layoutManager.findLastVisibleItemPosition()
                currentFirstPosition = layoutManager.findFirstVisibleItemPosition()

                if (isOnScreen()) {
                    binding.collapsingAllTeamLayout.apply {
                        viewGoneAnimator(this)
                    }

                } else {
                    binding.collapsingAllTeamLayout.apply {
                        viewVisibleAnimator(this)
                    }
                }

            }
        })

    }

    private fun isOnScreen(): Boolean =
        highLightPosition in currentFirstPosition..currentLastPosition


    private fun updateCollapsingLayout(team: AllTeamsDisplayModel) {
        binding.allTeamId.text = team.rank.toString()
        binding.allTeamCity.text = team.displayName
        if (team.average != 0) {
            binding.allTeamActivity.text = team.average.toString()
        } else {
            binding.allTeamActivity.text = getString(R.string.no_activity)
        }
    }


    private fun viewGoneAnimator(view: View) {
        binding.recyclerViewAllTeam.apply {
            setPadding(0, 0, 0, 0)
        }
        if (!isAnimationShowing) {
            isAnimationShowing = true
            view.animate()
                .translationY(0F)
                .setDuration(animationDuration)
                .interpolator = LinearInterpolator()
        }
    }

    private fun viewVisibleAnimator(view: View) {
        setPadding(view)
        if (isAnimationShowing) {
            view.animate().apply {
                translationY(view.height.toFloat())
                translationZ(100F)
                duration = animationDuration
                interpolator = LinearInterpolator()
            }
            isAnimationShowing = false
        }
    }

    private fun setPadding(view: View) {
        if (currentFirstPosition == 0) {
            binding.recyclerViewAllTeam.animate().apply {
                translationY(view.height.toFloat())
                duration = animationDuration
                interpolator = LinearInterpolator()
            }
        } else {
            binding.recyclerViewAllTeam.animate().apply {
                translationY(0F)
                duration = animationDuration
                interpolator = LinearInterpolator()
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_DISTANCE_DIALOG -> {
                    activeDistanceFilter[0] = data?.getStringExtra("step").toString()
                    viewModel.setActiveDistanceFilter(activeDistanceFilter[0])
                    recyclerAdapter?.notifyDataSetChanged()
                }
                REQUEST_TIME_PERIOD_DIALOG -> {
                    activeTimePeriodFilter[0] = data?.getStringExtra("time").toString()
                    viewModel.setActiveTimePeriodFilter(activeTimePeriodFilter[0])
                    recyclerAdapter?.notifyDataSetChanged()
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
        viewModel.updateAllTeams()
        viewModel.updateDistanceFilters()
        viewModel.updateTimePeriodFilters()
        viewModel.updateActiveDistanceFilter()
        viewModel.updateActiveTimePeriodFilter()

    }
}

