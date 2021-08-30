package com.antonchuraev.becomebetter.fragments.bottomNavigation.addGoal.createGoalFromTemplate

import android.content.Context
import com.antonchuraev.becomebetter.R
import com.antonchuraev.becomebetter.base.BasePresenter
import com.antonchuraev.becomebetter.dataClasses.Goal
import moxy.InjectViewState

@InjectViewState
class CreateGoalFromTemplatesPresenter: BasePresenter<CreateGoalFromTemplatesView>() {

    fun loadTemplates(context: Context) {
        val templates = mutableListOf<Goal>()

        templates.add(Goal( -1, context.getString(R.string.dont_smoke_30_days) , duration = 4F , context.getString(R.string.quit_smoking), priority = 2F , progressType = Goal.ProgressType.DAYS))
        templates.add(Goal(-2,context.getString(R.string.start_running_regulary)  , duration = 0F , description = null, priority = 1F , progressType = Goal.ProgressType.DAYS))
        templates.add(Goal(-3,context.getString(R.string.read_30_min)  , duration = 0F , null , priority = 1F , progressType = Goal.ProgressType.DAYS))
        templates.add(Goal(-6, context.getString(R.string.accumulate_money) , duration = 0F ,  priority = 2F))

        viewState.showTemplates(templates)
    }

}
