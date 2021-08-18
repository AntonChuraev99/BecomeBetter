package com.antonchuraev.becomebetter.fragments.bottomNavigation.allGoals.myGoals

import com.antonchuraev.becomebetter.base.BasePresenter
import com.antonchuraev.becomebetter.dataClasses.Goal
import moxy.InjectViewState

@InjectViewState
class MyGoalsGoalsPresenter: BasePresenter<MyGoalsView>() {

    fun loadActiveGoals(){
        val templates = mutableListOf<Goal>()

        templates.forEach { it.isActive = true }

        viewState.showActiveGoals(templates)
    }

    fun loadDisabledGoals(){

        viewState.showDisabledGoals(mutableListOf<Goal>())
    }

}
