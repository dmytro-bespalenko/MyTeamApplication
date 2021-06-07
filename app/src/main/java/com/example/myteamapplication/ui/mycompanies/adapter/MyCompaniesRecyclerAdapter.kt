package com.example.myteamapplication.ui.mycompanies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myteamapplication.R
import com.example.myteamapplication.ui.customview.CustomFilterView

class MyCompaniesRecyclerAdapter(
    private val recyclerAdapterData: RecyclerAdapterData,
    private val listener: OnItemClickListener

) : RecyclerView.Adapter<RecyclerView.ViewHolder>(

) {

    private var TYPE_ITEM_ZERO = 0
    private var TYPE_ITEM_OTHER = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            TYPE_ITEM_ZERO -> ViewHolderZeroPosition(
                LayoutInflater.from(parent.context).inflate(R.layout.adapter_filter, parent, false)
            )
            else -> ViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.my_companies_card, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

            if (holder.itemViewType == 0) {
                val holderZero = holder as ViewHolderZeroPosition
                holderZero.step.setTextView(recyclerAdapterData.activeDistanceFilter[0])
                holderZero.time.setTextView(recyclerAdapterData.activeTimePeriodFilter[0])

            } else {
                val mutableHolder: ViewHolder = holder as ViewHolder
                mutableHolder.myCompaniesId?.text =
                    recyclerAdapterData.myCompaniesDisplayModel[position - 1].rank.toString()
                mutableHolder.myCompanyName?.text =
                    recyclerAdapterData.myCompaniesDisplayModel[position].displayName
                mutableHolder.myCompanySteps?.text =
                    recyclerAdapterData.myCompaniesDisplayModel[position].totalDouble.toString()
            }

    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            TYPE_ITEM_ZERO
        } else {
            TYPE_ITEM_OTHER
        }
    }


    override fun getItemCount(): Int {
        return recyclerAdapterData.myCompaniesDisplayModel.size
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


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var myCompaniesId: TextView? = null
        var myCompanyName: TextView? = null
        var myCompanySteps: TextView? = null

        init {
            myCompaniesId = itemView.findViewById(R.id.my_company_id)
            myCompanyName = itemView.findViewById(R.id.my_company_name)
            myCompanySteps = itemView.findViewById(R.id.my_company_distance)
        }

    }

    interface OnItemClickListener {
        fun onItemDistanceClick()
        fun onItemTimeFrameClick()

    }
}



