package com.antonchuraev.becomebetter.fragments.bottomNavigation.allGoals

import com.antonchuraev.becomebetter.base.BasePresenter
import com.antonchuraev.becomebetter.dataClasses.Goal
import com.antonchuraev.becomebetter.views.goals.GoalInListView
import moxy.InjectViewState

@InjectViewState
class AllGoalsPresenter: BasePresenter<AllGoalsView>() {

    /**
     * // TODO: 10.04.2021
     * проинициализировать 3 первых цели
     * если таких нету то добавить заглушку с предложением
     */
    fun initializeThreeFirstGoalsData(){

        val testGoals = listOf<Goal>( Goal(1, "первая") , Goal(2, "вторая") )

        viewState.setThreeFirstGoalViews( testGoals )
    }

}
