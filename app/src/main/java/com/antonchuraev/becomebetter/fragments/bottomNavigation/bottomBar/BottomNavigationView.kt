package com.antonchuraev.becomebetter.fragments.bottomNavigation.bottomBar

import com.antonchuraev.becomebetter.fragments.bottomNavigation.NavigationTab
import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface BottomNavigationView: MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun openFragmentByTab(tab: NavigationTab?)

}