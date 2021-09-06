package com.antonchuraev.becomebetter.fragments.tabs

import com.antonchuraev.becomebetter.fragments.bottomNavigation.bottomBar.NavigationTab
import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface TabNavigationView: MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun openFragmentByTab(tab: NavigationTab?)

}