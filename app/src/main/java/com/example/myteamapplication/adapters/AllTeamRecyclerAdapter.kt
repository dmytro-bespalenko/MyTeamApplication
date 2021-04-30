package com.example.myteamapplication.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myteamapplication.R
import com.example.myteamapplication.allteams.Result

class AllTeamRecyclerAdapter(private val allTeams: List<Result>) :

    RecyclerView.Adapter<AllTeamRecyclerAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.all_teams_card, parent, false)

        return MyViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.numberAllTeam?.text = allTeams[position].rank.toString()
        holder.cityAllTeam?.text = allTeams[position].displayName

        if (allTeams[position].average != 0) {
            holder.activityAllTeam?.text =
                allTeams[position].average.toString()
        } else {
            holder.activityAllTeam?.text = "no activity"

        }
    }

    override fun getItemCount(): Int {

        return allTeams.size
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
