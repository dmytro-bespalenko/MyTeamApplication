package com.example.myteamapplication.ui.mycompanies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myteamapplication.R
import com.example.myteamapplication.ui.mycompanies.veiwmodel.MyCompaniesDisplayModel

class MyCompaniesRecyclerAdapter(private val myCompaniesList: MutableList<MyCompaniesDisplayModel>) :
    RecyclerView.Adapter<MyCompaniesRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.my_companies_card, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.myCompanyName?.text = myCompaniesList[position].displayName
        holder.myCompanySteps?.text = myCompaniesList[position].totalDouble.toString()
        holder.avatar

    }

    override fun getItemCount(): Int {
        return myCompaniesList.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var myCompaniesId: TextView? = null
        var myCompanyName: TextView? = null
        var myCompanySteps: TextView? = null
        var avatar: Int? = null

        init {
//            myCompaniesId = itemView.findViewById(R.id.id_my_company)
            myCompanyName = itemView.findViewById(R.id.my_company_name)
            myCompanySteps = itemView.findViewById(R.id.my_company_steps)
            avatar = itemView.findViewById(R.id.avatar_my_companies)
        }

    }
}
