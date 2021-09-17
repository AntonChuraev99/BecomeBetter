package com.antonchuraev.becomebetter.fragments.screens.goalsTab

import android.content.Context
import com.antonchuraev.becomebetter.base.BasePresenter
import com.antonchuraev.becomebetter.dataClasses.Goal
import com.antonchuraev.becomebetter.fragments.bottomNavigation.bottomBar.NavigationTab
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import moxy.InjectViewState

@InjectViewState
class GoalsTabPresenter: BasePresenter<GoalsTabView>() {

    fun loadGoals(context:Context,screenType: NavigationTab) {

        CoroutineScope(Dispatchers.IO).launch {
            val list = getDatabase(context).goalsDao().getGoals().toMutableList()
            list.filter { it.progressType == screenType.relatedToGoalsType }?.let { filteredList->
                CoroutineScope(Dispatchers.Main).launch {
                    viewState.showGoals(filteredList)
                }
            }
        }
    }

    fun addGoal(goal: Goal, context:Context){
        CoroutineScope(Dispatchers.IO).launch {
            getDatabase(context).goalsDao().insert(goal)
            CoroutineScope(Dispatchers.Main).launch {
                viewState.reloadGoals()
            }
        }
    }

    fun updateGoal(goal: Goal, context:Context){
        CoroutineScope(Dispatchers.IO).launch {
            getDatabase(context).goalsDao().update(goal)
            CoroutineScope(Dispatchers.Main).launch {
                viewState.reloadGoals()
            }
        }
    }

    fun deleteGoal(goal: Goal, context:Context){
        CoroutineScope(Dispatchers.IO).launch {
            getDatabase(context).goalsDao().delete(goal)
            CoroutineScope(Dispatchers.Main).launch {
                viewState.reloadGoals()
            }
        }
    }

}
