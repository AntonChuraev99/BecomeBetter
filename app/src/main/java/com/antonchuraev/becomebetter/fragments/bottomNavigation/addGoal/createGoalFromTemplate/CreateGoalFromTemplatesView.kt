package com.antonchuraev.becomebetter.fragments.bottomNavigation.addGoal.createGoalFromTemplate

import com.antonchuraev.becomebetter.dataClasses.Goal
import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface CreateGoalFromTemplatesView: MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class )
    fun showTemplates(templates: MutableList<Goal>)

}