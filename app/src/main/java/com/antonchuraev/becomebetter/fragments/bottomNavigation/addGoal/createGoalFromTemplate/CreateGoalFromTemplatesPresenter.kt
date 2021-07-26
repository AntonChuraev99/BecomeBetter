package com.antonchuraev.becomebetter.fragments.bottomNavigation.addGoal.createGoalFromTemplate

import com.antonchuraev.becomebetter.base.BasePresenter
import com.antonchuraev.becomebetter.dataClasses.Template
import moxy.InjectViewState

@InjectViewState
class CreateGoalFromTemplatesPresenter: BasePresenter<CreateGoalFromTemplatesView>() {

    fun loadTemplates(){
        val templates = mutableListOf<Template>()

        templates.add(Template("Бросить курить"))
        templates.add(Template("Начать бегать") )
        templates.add(Template("Начать читать") )
        templates.add(Template("Бросить курить") )
        templates.add(Template("Бросить курить") )
        templates.add(Template("Бросить курить") )

        viewState.showTemplates(templates)
    }

}
