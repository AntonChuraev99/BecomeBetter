package com.antonchuraev.becomebetter.fragments.bottomNavigation.addGoal.createGoalFromTemplate

import com.antonchuraev.becomebetter.base.BasePresenter
import com.antonchuraev.becomebetter.dataClasses.Goal
import moxy.InjectViewState

@InjectViewState
class CreateGoalFromTemplatesPresenter: BasePresenter<CreateGoalFromTemplatesView>() {

    fun loadTemplates(){
        val templates = mutableListOf<Goal>()

        templates.add(Goal("Бросить курить"))
        templates.add(Goal("Начать бегать") )
        templates.add(Goal("Начать читать") )
        templates.add(Goal("Кушать только здоровую пищу") )
        templates.add(Goal("TODO Придумать") )
        templates.add(Goal("TODO Придумать") )

        viewState.showTemplates(templates)
    }

}
