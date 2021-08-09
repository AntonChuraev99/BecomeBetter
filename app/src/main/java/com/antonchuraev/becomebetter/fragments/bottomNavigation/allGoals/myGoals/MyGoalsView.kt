package com.antonchuraev.becomebetter.fragments.bottomNavigation.allGoals.myGoals

import android.view.View
import com.antonchuraev.becomebetter.dataClasses.Goal
import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface MyGoalsView: MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class )
    fun showActiveGoals(goals: MutableList<Goal>)

    @StateStrategyType(OneExecutionStateStrategy::class )
    fun showDisabledGoals(goals: MutableList<Goal>)
}