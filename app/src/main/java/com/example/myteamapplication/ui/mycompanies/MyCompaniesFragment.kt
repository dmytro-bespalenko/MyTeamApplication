package com.example.myteamapplication.ui.mycompanies

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myteamapplication.databinding.FragmentMyCompaniesBinding
import com.example.myteamapplication.ui.main.fragment.BaseFragment
import com.example.myteamapplication.ui.mycompanies.adapter.MyCompaniesRecyclerAdapter
import com.example.myteamapplication.ui.mycompanies.adapter.RecyclerAdapterData
import com.example.myteamapplication.ui.mycompanies.veiwmodel.MyCompaniesDisplayModel
import com.example.myteamapplication.ui.mycompanies.veiwmodel.MyCompaniesViewModel


const val REQUEST_DISTANCE_DIALOG = 0
const val REQUEST_TIME_PERIOD_DIALOG = 1

@Suppress("DEPRECATION")
@SuppressLint("NotifyDataSetChanged")
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class MyCompaniesFragment : BaseFragment<MyCompaniesViewModel, FragmentMyCompaniesBinding>(),
    MyCompaniesRecyclerAdapter.OnItemClickListener {

    private var myCompaniesList: MutableList<MyCompaniesDisplayModel> = mutableListOf()
    private var distanceFilterList: ArrayList<String> = ArrayList()
    private var timePeriodFiltersList: ArrayList<String> = ArrayList()
    private var activeDistanceFilter: MutableList<String> = mutableListOf()
    private var activeTimePeriodFilter: MutableList<String> = mutableListOf()
    private var rankCompany: MutableList<MyCompaniesDisplayModel> = mutableListOf()
    private var highLightPosition = 0
    private var currentLastPosition = 0
    private var currentFirstPosition = 0
    private var animationDuration = 0L
    private var isAnimationShowing = false
    private var positionItem: ArrayList<Int> = ArrayList()


    private var recyclerAdapter: MyCompaniesRecyclerAdapter? = MyCompaniesRecyclerAdapter(
        RecyclerAdapterData(
            myCompaniesList,
            activeDistanceFilter,
            activeTimePeriodFilter,
            rankCompany,
            positionItem
        ),
        this
    )

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentMyCompaniesBinding.inflate(inflater, container, false)

    override fun getViewModel(): Class<MyCompaniesViewModel> = MyCompaniesViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        animationDuration =
            resources.getInteger(android.R.integer.config_longAnimTime).toLong()
        binding.recyclerViewMyCompanies.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = recyclerAdapter
        }

        Log.d("TAG", "onViewCreated: $viewModel myCompanies")

        viewModel
            .getHighLightItem()
            .observe(viewLifecycleOwner,
                {
                    rankCompany.clear()
                    rankCompany.add(it)
                    updateCollapsingLayout(it)
                }
            )

        viewModel
            .getCompanies()
            .observe(viewLifecycleOwner,
                { c ->
                    myCompaniesList.clear()
                    myCompaniesList.addAll(c)
                    recyclerAdapter!!.notifyDataSetChanged()
                })

        getActiveDistance(activeDistanceFilter)

        getActiveTimePeriod(activeTimePeriodFilter)

        getDistance(distanceFilterList)

        getTimePeriod(timePeriodFiltersList)

        viewModel.getHighLightPosition().observe(viewLifecycleOwner, {
            positionItem.clear()
            highLightPosition = it
            positionItem.add(it)
            if (!isOnScreen()) {
                viewVisibleAnimator(binding.collapsingLayout)
            } else {
                viewGoneAnimator(binding.collapsingLayout)
            }
        })

        binding.recyclerViewMyCompanies.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager =
                    binding.recyclerViewMyCompanies.layoutManager as LinearLayoutManager

                currentLastPosition = layoutManager.findLastVisibleItemPosition()
                currentFirstPosition = layoutManager.findFirstVisibleItemPosition()

                if (isOnScreen()) {
                    binding.collapsingLayout.apply {
                        viewGoneAnimator(this)
                    }

                } else {
                    binding.collapsingLayout.apply {
                        viewVisibleAnimator(this)
                    }
                }

            }
        })
    }

    private fun isOnScreen(): Boolean =
        highLightPosition in currentFirstPosition..currentLastPosition


    private fun viewGoneAnimator(view: View) {
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
            view.animate()
                .translationY(view.height.toFloat())
                .translationZ(100F)
                .setDuration(animationDuration)
                .interpolator = LinearInterpolator()
            isAnimationShowing = false
        }

    }


    private fun setPadding(view: View) {
        if (currentFirstPosition == 0) {
            binding.recyclerViewMyCompanies.animate().apply {
                translationY(view.height.toFloat())
                duration = animationDuration
                interpolator = LinearInterpolator()
            }
        } else {
            binding.recyclerViewMyCompanies.animate().apply {
                translationY(0F)
                duration = animationDuration
                interpolator = LinearInterpolator()
            }
        }
    }

    private fun updateCollapsingLayout(company: MyCompaniesDisplayModel) {
        binding.myCompanyId.text = company.rank.toString()
        binding.myCompanyName.text = company.displayName
        binding.myCompanyDistance.text = company.totalDouble.toString()

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
        viewModel.updateCompanies()
        viewModel.updateDistanceFilters()
        viewModel.updateTimePeriodFilters()
        viewModel.updateActiveDistanceFilter()
        viewModel.updateActiveTimePeriodFilter()
    }

}

