package com.example.myteamapplication.base

import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myteamapplication.allteams.AllTeamsFragment
import com.example.myteamapplication.mycompanies.MyCompaniesFragment
import com.example.myteamapplication.myteam.MyTeamFragment

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