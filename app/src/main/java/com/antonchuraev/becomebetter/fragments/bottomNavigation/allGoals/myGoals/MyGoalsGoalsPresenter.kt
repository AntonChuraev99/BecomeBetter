package com.antonchuraev.becomebetter.fragments.bottomNavigation.allGoals.myGoals

import android.content.Context
import com.antonchuraev.becomebetter.base.BasePresenter
import com.antonchuraev.becomebetter.dataClasses.Goal
import kotlinx.coroutines.*
import moxy.InjectViewState
import kotlin.coroutines.coroutineContext

@InjectViewState
class MyGoalsGoalsPresenter: BasePresenter<MyGoalsView>() {

    fun loadActiveGoals(context: Context){


            CoroutineScope(Dispatchers.IO).launch {
            val list = getDatabase(context).goalsDao().getGoals(true).toMutableList()
            CoroutineScope(Dispatchers.Main).launch {
                viewState.showActiveGoals(list)
            }
        }
    }

    fun loadDisabledGoals(context: Context){

        CoroutineScope(Dispatchers.IO).launch {
            val list = getDatabase(context).goalsDao().getGoals(false).toMutableList()
            CoroutineScope(Dispatchers.Main).launch {
                viewState.showDisabledGoals(list)
            }
        }

    }

}
