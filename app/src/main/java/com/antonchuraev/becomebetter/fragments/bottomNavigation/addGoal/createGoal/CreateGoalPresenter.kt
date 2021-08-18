package com.antonchuraev.becomebetter.fragments.bottomNavigation.addGoal.createGoal

import android.content.Context
import com.antonchuraev.becomebetter.base.BasePresenter
import com.antonchuraev.becomebetter.dataClasses.Goal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import moxy.InjectViewState

@InjectViewState
class CreateGoalPresenter: BasePresenter<CreateGoalView>() {

    fun createGoal(goal:Goal , context:Context){
        CoroutineScope(Dispatchers.IO).launch {
            getDatabase(context).goalsDao().insert(goal)
            CoroutineScope(Dispatchers.Main).launch {
                viewState.goalCreated()
            }

        }
    }

}
