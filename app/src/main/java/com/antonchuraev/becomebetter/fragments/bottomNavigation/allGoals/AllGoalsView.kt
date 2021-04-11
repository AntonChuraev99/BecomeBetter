package com.antonchuraev.becomebetter.fragments.bottomNavigation.allGoals

import android.view.View
import com.antonchuraev.becomebetter.dataClasses.Goal
import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface AllGoalsView: MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class )
    fun setThreeFirstGoalViews(views:List<Goal>)


}