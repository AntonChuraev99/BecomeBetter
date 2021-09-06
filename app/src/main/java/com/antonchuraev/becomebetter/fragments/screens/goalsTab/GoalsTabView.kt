package com.antonchuraev.becomebetter.fragments.screens.goalsTab

import com.antonchuraev.becomebetter.dataClasses.Goal
import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface GoalsTabView: MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class )
    fun showGoals(goals:List<Goal>)

}