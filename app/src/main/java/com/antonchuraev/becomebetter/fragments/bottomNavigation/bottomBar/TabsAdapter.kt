package com.antonchuraev.becomebetter.fragments.bottomNavigation.bottomBar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.antonchuraev.becomebetter.R
import com.antonchuraev.becomebetter.fragments.bottomNavigation.NavigationTab
import com.antonchuraev.becomebetter.fragments.bottomNavigation.addGoal.AddGoalFragment
import com.antonchuraev.becomebetter.fragments.bottomNavigation.addGoal.createGoal.CreateGoalFragment
import com.antonchuraev.becomebetter.fragments.bottomNavigation.addGoal.createGoalFromTemplate.CreateGoalFromTemplatesFragment
import com.antonchuraev.becomebetter.fragments.bottomNavigation.allGoals.AllGoalsFragment

/**
 * tabs = NavigationTab
 */
class TabsAdapter(fragment:Fragment) : FragmentStateAdapter(fragment) {



    override fun createFragment(position: Int): Fragment = when(position){
        1->AddGoalFragment()


        else->AllGoalsFragment()
    }

    override fun getItemCount(): Int = NavigationTab.values().size
}