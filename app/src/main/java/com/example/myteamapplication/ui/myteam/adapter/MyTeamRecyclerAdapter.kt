package com.example.myteamapplication.ui.myteam.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.myteamapplication.R
import com.example.myteamapplication.ui.customview.CustomFilterView
import com.example.myteamapplication.ui.myteam.MyTeamFragment

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)

class MyTeamRecyclerAdapter(
    private val recyclerAdapterData: MyTeamAdapterData,
    private val listener: MyTeamFragment
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(


) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return ViewHolderZeroPosition(
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_filter, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val holderZero = holder as ViewHolderZeroPosition
        if (recyclerAdapterData.activeDistanceFilter.isNotEmpty() && recyclerAdapterData.activeTimePeriodFilter.isNotEmpty()) {
            holderZero.step.setTextView(recyclerAdapterData.activeDistanceFilter[0])
            holderZero.time.setTextView(recyclerAdapterData.activeTimePeriodFilter[0])
        }

    }


    override fun getItemCount(): Int {
        return 1
    }

    inner class ViewHolderZeroPosition(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        var step: CustomFilterView = itemView.findViewById(R.id.step)
        var time: CustomFilterView = itemView.findViewById(R.id.time)

        init {
            step.setOnClickListener { listener.onItemDistanceClick() }
            time.setOnClickListener { listener.onItemTimeFrameClick() }

        }


        override fun onClick(v: View?) {

            when (itemView) {
                step -> listener.onItemDistanceClick()
                time -> listener.onItemTimeFrameClick()
            }

        }
    }

    interface OnItemClickListener {
        fun onItemDistanceClick()
        fun onItemTimeFrameClick()

    }
}



