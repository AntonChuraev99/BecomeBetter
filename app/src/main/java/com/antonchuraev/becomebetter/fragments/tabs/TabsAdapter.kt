package com.antonchuraev.becomebetter.fragments.tabs

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.antonchuraev.becomebetter.fragments.bottomNavigation.bottomBar.NavigationTab
import com.antonchuraev.becomebetter.fragments.screens.goalsTab.GoalsTabFragment

/**
 * tabs = NavigationTab
 */
class TabsAdapter(fragment:Fragment) : FragmentStateAdapter(fragment) {

    override fun createFragment(position: Int): Fragment = GoalsTabFragment.newInstance(
        NavigationTab.getByPosition(position))

    override fun getItemCount(): Int = NavigationTab.values().size
}