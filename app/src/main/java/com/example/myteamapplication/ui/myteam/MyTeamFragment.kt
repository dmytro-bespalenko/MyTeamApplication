package com.example.myteamapplication.ui.myteam

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myteamapplication.R
import com.example.myteamapplication.ui.customview.CustomFilterView
import com.example.myteamapplication.ui.main.fragment.BasicFragment


class MyTeamFragment : BasicFragment() {



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        var customFilterView: CustomFilterView? = CustomFilterView(view.context)
//
//        customFilterView?.setTextView("Step")

    }


    override fun onResume() {
        super.onResume()
        Log.d("TAG", "onResume: $this")

    }


}