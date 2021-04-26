package com.example.myteamapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myteamapplication.R
import com.example.myteamapplication.allteams.AllTeamModel

class AllTeamRecyclerAdapter(private val allTeams: List<AllTeamModel>) :
    RecyclerView.Adapter<AllTeamRecyclerAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.all_teams_card, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.numberAllTeam?.text = allTeams[position].toString()
        holder.cityAllTeam?.text = "all team"
        holder.activityAllTeam?.text = "activity"

    }

    override fun getItemCount(): Int {

        return 1
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var numberAllTeam: TextView? = null
        var cityAllTeam: TextView? = null
        var activityAllTeam: TextView? = null


        init {

            numberAllTeam = itemView.findViewById(R.id.number_all_team_textView)
            cityAllTeam = itemView.findViewById(R.id.city_all_team_textView)
            activityAllTeam = itemView.findViewById(R.id.activity_all_team_textView)

        }
    }

}
