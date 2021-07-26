package com.antonchuraev.becomebetter.fragments.bottomNavigation.addGoal.createGoalFromTemplate

import com.antonchuraev.becomebetter.dataClasses.Template
import moxy.MvpView

interface CreateGoalFromTemplatesView: MvpView {

    fun showTemplates(templates: MutableList<Template>)

}