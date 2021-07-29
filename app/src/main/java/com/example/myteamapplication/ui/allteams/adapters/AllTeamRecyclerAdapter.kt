package com.example.myteamapplication.ui.allteams.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.myteamapplication.R

class AllTeamRecyclerAdapter(private val recyclerAdapterData: RecyclerAdapterData) :

    RecyclerView.Adapter<AllTeamRecyclerAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.all_teams_card, parent, false)

        return MyViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.numberAllTeam?.text = recyclerAdapterData.allTeamsList[position].rank.toString()
        holder.cityAllTeam?.text = recyclerAdapterData.allTeamsList[position].displayName

        if (recyclerAdapterData.allTeamsList[position].average != 0) {
            holder.activityAllTeam?.text =
                recyclerAdapterData.allTeamsList[position].average.toString()
        } else {
            holder.activityAllTeam?.text = "no activity"
        }

        if (recyclerAdapterData.rank[0].teamId == recyclerAdapterData.allTeamsList[position].teamId) {
            holder.cardView?.setBackgroundResource(R.color.Aquamarine)

        } else {
            holder.cardView?.setBackgroundColor(Color.WHITE)
        }
    }

    override fun getItemCount(): Int {

        return recyclerAdapterData.allTeamsList.size
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var numberAllTeam: TextView? = null
        var cityAllTeam: TextView? = null
        var activityAllTeam: TextView? = null
        var cardView: CardView? = null


        init {
            numberAllTeam = itemView.findViewById(R.id.number_all_team_textView)
            cityAllTeam = itemView.findViewById(R.id.city_all_team_textView)
            activityAllTeam = itemView.findViewById(R.id.activity_all_team_textView)
            cardView = itemView.findViewById(R.id.cardView_all_teams)
        }
    }

}
