package com.example.myteamapplication.allteams

import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.myteamapplication.base.BasicFragment

class AllTeamsFragment : BasicFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        Log.d("TAG", "onResume: $this")
    }
}