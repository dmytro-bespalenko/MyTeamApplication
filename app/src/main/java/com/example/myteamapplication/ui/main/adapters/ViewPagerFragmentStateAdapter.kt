package com.example.myteamapplication.ui.main.adapters

import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myteamapplication.ui.main.NUM_PAGES
import com.example.myteamapplication.ui.allteams.AllTeamsFragment
import com.example.myteamapplication.ui.mycompanies.MyCompaniesFragment
import com.example.myteamapplication.ui.myteam.MyTeamFragment

class ViewPagerFragmentStateAdapter(@NonNull fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {


    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MyTeamFragment()
            1 -> AllTeamsFragment()
            else -> MyCompaniesFragment()
        }

    }

    override fun getItemCount(): Int {
        return NUM_PAGES
    }


}