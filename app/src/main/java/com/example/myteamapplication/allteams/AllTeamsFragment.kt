package com.example.myteamapplication.allteams

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myteamapplication.R
import com.example.myteamapplication.base.BasicFragment

class AllTeamsFragment : BasicFragment() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView_all_ream)
        recyclerView.layoutManager = LinearLayoutManager(context)



    }

    override fun onResume() {
        super.onResume()
        Log.d("TAG", "onResume: $this")
    }
}