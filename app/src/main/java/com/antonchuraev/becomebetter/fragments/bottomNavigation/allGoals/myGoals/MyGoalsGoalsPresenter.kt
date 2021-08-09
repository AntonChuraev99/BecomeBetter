package com.antonchuraev.becomebetter.fragments.bottomNavigation.allGoals.myGoals

import com.antonchuraev.becomebetter.base.BasePresenter
import com.antonchuraev.becomebetter.dataClasses.Goal
import moxy.InjectViewState

@InjectViewState
class MyGoalsGoalsPresenter: BasePresenter<MyGoalsView>() {

    fun loadActiveGoals(){
        val templates = mutableListOf<Goal>()

        templates.add(Goal("Бросить курить" , duration = 4F , "Бросить курить за месяц", priority = 2F , progress = 1))
        templates.add(Goal("Начать регулярно бегать" , duration = 0F , "Регулярно бегать", priority = 1F, progress = 50))
        templates.add(Goal("Ежедневно читать минимум по 30 минут" , duration = 0F , null , priority = 1F))
        templates.add(Goal("Кушать здоровую пищу в течение года" , duration = 5F , null, priority = 1F))
        templates.add(Goal("Похудеть" , duration = 0F , "Похудеть на [введите кол-во кг]", priority = 2F, progress = 100))

        templates.forEach { it.isSelected = true }

        viewState.showActiveGoals(templates)
    }

    fun loadDisabledGoals(){

        viewState.showDisabledGoals(mutableListOf<Goal>())
    }

}
