package com.antonchuraev.becomebetter.fragments.bottomNavigation.addGoal.createGoal

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface CreateGoalView: MvpView {

    @StateStrategyType(AddToEndStrategy::class )
    fun finishWork()

}