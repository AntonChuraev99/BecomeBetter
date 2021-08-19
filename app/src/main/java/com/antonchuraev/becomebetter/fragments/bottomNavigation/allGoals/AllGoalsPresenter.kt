package com.antonchuraev.becomebetter.fragments.bottomNavigation.allGoals

import android.content.Context
import com.antonchuraev.becomebetter.base.BasePresenter
import com.antonchuraev.becomebetter.dataClasses.Goal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import moxy.InjectViewState

@InjectViewState
class AllGoalsPresenter: BasePresenter<AllGoalsView>() {

    /**
     *
     * проинициализировать активные
     * если таких нету то добавить заглушку с предложением
     */
    fun loadActiveGoals(context:Context){

        CoroutineScope(Dispatchers.IO).launch {

            val goals = getDatabase(context).goalsDao().getGoals(true)

            CoroutineScope(Dispatchers.Main).launch {
                viewState.showActiveGoals(goals)
            }

        }
    }

    fun updateGoal(goal: Goal, context:Context){
        CoroutineScope(Dispatchers.IO).launch {
            getDatabase(context).goalsDao().update(goal)
        }
    }

}
