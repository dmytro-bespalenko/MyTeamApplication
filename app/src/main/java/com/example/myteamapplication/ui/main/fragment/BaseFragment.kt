package com.example.myteamapplication.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.myteamapplication.TeamApplication
import com.example.myteamapplication.room.repositories.RoomDistanceFilterRepository
import com.example.myteamapplication.ui.customview.SelectDistanceDialogFragment
import com.example.myteamapplication.ui.customview.SelectTimePeriodDialogFragment
import com.example.myteamapplication.ui.main.viewmodel.BaseFactory
import com.example.myteamapplication.ui.main.viewmodel.BaseViewModel

const val REQUEST_DISTANCE_DIALOG = 0
const val REQUEST_TIME_PERIOD_DIALOG = 1
const val TAG = "MyCustomFragment"

abstract class BaseFragment<VM : AndroidViewModel, B : ViewBinding> : Fragment() {


    protected lateinit var binding: B

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getFragmentBinding(inflater, container)

        return binding.root
    }

    val viewModel: VM by lazy {
        val factory =
            BaseFactory(RoomDistanceFilterRepository.getInstance(TeamApplication.instance))

        ViewModelProvider(this, factory).get(getViewModel())
    }

    abstract fun getViewModel(): Class<VM>

    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): B

    fun onDistanceClick(distanceFilterList: ArrayList<String>, fragment: Fragment) {
        SelectDistanceDialogFragment.newInstance(distanceFilterList).apply {
            setTargetFragment(fragment, REQUEST_DISTANCE_DIALOG)
            show(
                fragment.parentFragmentManager,
                TAG
            )
        }

    }

    fun onTimeClick(timePeriodFiltersList: ArrayList<String>, fragment: Fragment) {
        SelectTimePeriodDialogFragment.newInstance(timePeriodFiltersList).apply {
            setTargetFragment(fragment, REQUEST_TIME_PERIOD_DIALOG)
            show(
                fragment.parentFragmentManager,
                TAG
            )
        }
    }

    fun getActiveDistance(activeDistanceFilter: MutableList<String>) {
        val baseViewModel = viewModel as BaseViewModel
        baseViewModel.getActiveDistanceFilter()
            .observe(viewLifecycleOwner,
                { ad ->
                    activeDistanceFilter.clear()
                    activeDistanceFilter.add(ad)
                }
            )
    }

    fun getActiveTimePeriod(
        activeTimePeriodFilter: MutableList<String>
    ) {
        val baseViewModel = viewModel as BaseViewModel

        baseViewModel.getActiveTimePeriodFilter()
            .observe(viewLifecycleOwner,
                { tp ->
                    activeTimePeriodFilter.clear()
                    activeTimePeriodFilter.add(tp)
                }
            )
    }

    fun getDistance(distanceFilterList: MutableList<String>) {
        val baseViewModel = viewModel as BaseViewModel

        baseViewModel.getDistanceFilters()
            .observe(viewLifecycleOwner,
                { f ->
                    distanceFilterList.clear()
                    distanceFilterList.addAll(f)
                }

            )
    }

    fun getTimePeriod(timePeriodFiltersList: MutableList<String>) {
        val baseViewModel = viewModel as BaseViewModel

        baseViewModel.getTimePeriodFilters()
            .observe(viewLifecycleOwner,
                { f ->
                    timePeriodFiltersList.clear()
                    timePeriodFiltersList.addAll(f)
                }
            )
    }


}