package com.example.myteamapplication.ui.myteam.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.myteamapplication.databinding.AdapterFilterBinding
import com.example.myteamapplication.ui.myteam.MyTeamFragment

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)

class MyTeamRecyclerAdapter(
    private val recyclerAdapterData: MyTeamAdapterData,
    private val listener: MyTeamFragment
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(


) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolderZeroPosition(
            AdapterFilterBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val holderFilters = holder as ViewHolderZeroPosition
        if (recyclerAdapterData.activeDistanceFilter.isNotEmpty() && recyclerAdapterData.activeTimePeriodFilter.isNotEmpty()) {
            holderFilters.binding.step.setTextView(recyclerAdapterData.activeDistanceFilter[0])
            holderFilters.binding.time.setTextView(recyclerAdapterData.activeTimePeriodFilter[0])

        }

    }

    override fun getItemCount(): Int {
        return 1
    }

    inner class ViewHolderZeroPosition(val binding: AdapterFilterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.step.setOnClickListener { listener.onItemDistanceClick() }
            binding.time.setOnClickListener { listener.onItemTimeFrameClick() }
        }

    }

    interface OnItemClickListener {
        fun onItemDistanceClick()
        fun onItemTimeFrameClick()

    }
}



