package com.antonchuraev.becomebetter.fragments.bottomNavigation.addGoal.createGoalFromTemplate

import com.antonchuraev.becomebetter.base.BasePresenter
import com.antonchuraev.becomebetter.dataClasses.Goal
import moxy.InjectViewState

@InjectViewState
class CreateGoalFromTemplatesPresenter: BasePresenter<CreateGoalFromTemplatesView>() {

    fun loadTemplates(){
        val templates = mutableListOf<Goal>()

        templates.add(Goal( -1, "Не курить 30 дней" , duration = 4F , "Бросить курить за месяц", priority = 2F , progressType = Goal.ProgressType.DAYS))
        templates.add(Goal(-2,"Начать регулярно бегать" , duration = 0F , "Регулярно бегать", priority = 1F , progressType = Goal.ProgressType.DAYS))
        templates.add(Goal(-3,"Ежедневно читать минимум по 30 минут" , duration = 0F , null , priority = 1F , progressType = Goal.ProgressType.DAYS))
        templates.add(Goal(-4,"Кушать здоровую пищу в течение года" , duration = 5F , null, priority = 1F , progressType = Goal.ProgressType.DAYS))
        templates.add(Goal(-5,"Похудеть" , duration = 0F , "Похудеть на [введите кол-во кг]", priority = 2F))
        templates.add(Goal(-6, "Накопить 100 тысяч" , duration = 0F ,  priority = 2F))

        viewState.showTemplates(templates)
    }

}
