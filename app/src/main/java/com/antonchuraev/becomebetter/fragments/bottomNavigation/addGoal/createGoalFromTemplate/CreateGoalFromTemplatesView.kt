package com.antonchuraev.becomebetter.fragments.bottomNavigation.addGoal.createGoalFromTemplate

import moxy.MvpView

interface CreateGoalFromTemplatesView: MvpView {

    fun showTemplates(templates: MutableList<String>)

}