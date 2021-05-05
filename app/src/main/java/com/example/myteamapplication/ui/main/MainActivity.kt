package com.example.myteamapplication.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import androidx.viewpager2.widget.ViewPager2
import com.example.myteamapplication.R
import com.example.myteamapplication.ui.main.adapters.ViewPagerFragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


const val NUM_PAGES = 3

class MainActivity : AppCompatActivity() {


    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tabLayout: TabLayout = findViewById(R.id.tab_layout)

        viewPager = findViewById(R.id.team_viewPager2)
        val pagerAdapter = ViewPagerFragmentStateAdapter(this);

        viewPager.adapter = pagerAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "MY TEAM"
                1 -> tab.text = "ALL TEAMS"
                2 -> tab.text = "MY COMPANY"
            }

            viewPager.setCurrentItem(tab.position, true)
        }.attach()

        Log.d("TAG", "onCreate: ")
    }


}

