package com.example.myteamapplication.ui.mycompanies.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myteamapplication.R
import com.example.myteamapplication.databinding.AdapterFilterBinding
import com.example.myteamapplication.databinding.MyCompaniesCardBinding
import com.example.myteamapplication.ui.mycompanies.veiwmodel.MyCompaniesDisplayModel
import com.squareup.picasso.Picasso

private var TYPE_ITEM_FIRST = 0
private var TYPE_ITEM_OTHER = 1

data class MyCompaniesRecyclerAdapter(
    private val recyclerAdapterData: RecyclerAdapterData,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(

) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val bindingFilter = AdapterFilterBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        val bindingMyCompanies = MyCompaniesCardBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)


        return when (viewType) {
            TYPE_ITEM_FIRST ->
                ViewHolderFirstPosition(bindingFilter)

            else -> ViewHolder(bindingMyCompanies)
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder.itemViewType == TYPE_ITEM_FIRST) {
            val holderZero = holder as ViewHolderFirstPosition

            holderZero.binding.step.setTextView(recyclerAdapterData.activeDistanceFilter[0])
            holderZero.binding.time.setTextView(recyclerAdapterData.activeTimePeriodFilter[0])


        } else {
            val mutableHolder: ViewHolder = holder as ViewHolder

            mutableHolder.bindGalleryItem(recyclerAdapterData.myCompaniesDisplayModel[position - 1])

            if (recyclerAdapterData.positionItem.isNotEmpty() && recyclerAdapterData.positionItem[0] == position
            ) {
                mutableHolder.binding.companyLayout.setBackgroundResource(R.color.Aquamarine)

            } else {
                mutableHolder.binding.companyLayout.setBackgroundColor(Color.WHITE);
            }

            mutableHolder.binding.myCompanyId.text =
                recyclerAdapterData.myCompaniesDisplayModel[position - 1].rank.toString()
            mutableHolder.binding.myCompanyName.text =
                recyclerAdapterData.myCompaniesDisplayModel[position].displayName
            mutableHolder.binding.myCompanyDistance.text =
                recyclerAdapterData.myCompaniesDisplayModel[position].totalDouble.toString()


        }

    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            TYPE_ITEM_FIRST
        } else {
            TYPE_ITEM_OTHER
        }
    }


    override fun getItemCount(): Int {
        return recyclerAdapterData.myCompaniesDisplayModel.size
    }

    inner class ViewHolderFirstPosition(val binding: AdapterFilterBinding) :
        RecyclerView.ViewHolder(binding.root) {


        init {
            binding.step.setOnClickListener { listener.onItemDistanceClick() }
            binding.time.setOnClickListener { listener.onItemTimeFrameClick() }
        }

    }

    inner class ViewHolder(val binding: MyCompaniesCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindGalleryItem(galleryItem: MyCompaniesDisplayModel) {

            Picasso.get()
                .load(galleryItem.avatar)
                .placeholder(R.drawable.ic_baseline_account_box_24)
                .into(binding.myCompanyNameAvatar)
        }


    }


    interface OnItemClickListener {
        fun onItemDistanceClick()
        fun onItemTimeFrameClick()
    }


}



