package com.example.myteamapplication.ui.allteams.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myteamapplication.R
import com.example.myteamapplication.databinding.AdapterFilterBinding
import com.example.myteamapplication.databinding.AllTeamsCardBinding

@SuppressLint("SetTextI18n")

private var TYPE_ITEM_FIRST = 0
private var TYPE_ITEM_OTHER = 1

class AllTeamRecyclerAdapter(
    private val recyclerAdapterData: RecyclerAdapterData,
    private val listener: OnItemClickListener
) :

    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val bindingFilter = AdapterFilterBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        val bindingAllTeams = AllTeamsCardBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return when (viewType) {
            TYPE_ITEM_FIRST ->
                MyViewHolderFirstPosition(bindingFilter)
            else -> {
                MyViewHolder(bindingAllTeams)
            }

        }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder.itemViewType == TYPE_ITEM_FIRST) {
            val myViewHolderFirstPosition = holder as MyViewHolderFirstPosition
            myViewHolderFirstPosition.binding.step.setTextView(recyclerAdapterData.activeDistanceFilter[0])
            myViewHolderFirstPosition.binding.time.setTextView(recyclerAdapterData.activeTimePeriodFilter[0])

        } else {
            val myViewHolder = holder as MyViewHolder
            myViewHolder.binding.numberAllTeamTextView.text =
                recyclerAdapterData.allTeamsList[position - 1].rank.toString()
            myViewHolder.binding.cityAllTeamTextView.text =
                recyclerAdapterData.allTeamsList[position].displayName

            if (recyclerAdapterData.allTeamsList[position].average != 0) {
                myViewHolder.binding.activityAllTeamTextView.text =
                    recyclerAdapterData.allTeamsList[position].average.toString()
            } else {
                myViewHolder.binding.activityAllTeamTextView.text = "no activity"
            }

            if (recyclerAdapterData.positionItem.isNotEmpty() && position == recyclerAdapterData.positionItem[0]) {
                myViewHolder.binding.cardViewAllTeams.setBackgroundResource(R.color.Aquamarine)
            } else {
                myViewHolder.binding.cardViewAllTeams.setBackgroundColor(Color.WHITE)
            }
        }

    }

    override fun getItemCount(): Int {
        return recyclerAdapterData.allTeamsList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            TYPE_ITEM_FIRST
        } else {
            TYPE_ITEM_OTHER
        }
    }

    inner class MyViewHolderFirstPosition(val binding: AdapterFilterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.step.setOnClickListener { listener.onItemDistanceClick() }
            binding.time.setOnClickListener { listener.onItemTimeFrameClick() }
        }

    }

    inner class MyViewHolder(val binding: AllTeamsCardBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    interface OnItemClickListener {
        fun onItemDistanceClick()
        fun onItemTimeFrameClick()
    }
}
