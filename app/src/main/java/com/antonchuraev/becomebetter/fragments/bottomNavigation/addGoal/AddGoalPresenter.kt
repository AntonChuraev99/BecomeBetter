package com.antonchuraev.becomebetter.fragments.bottomNavigation.addGoal

import com.antonchuraev.becomebetter.base.BasePresenter
import moxy.InjectViewState

@InjectViewState
class AddGoalPresenter: BasePresenter<AddGoalView>() {

	fun toCreateYourGoalPressed(){
		// TODO: 05.07.2021 здесь можно что то проверять / тестировать / подгружать настройки дял слайдеров
		viewState.toCreateYourGoal()
	}

	fun toCreateGoalFromTemplatesPressed(){
		// TODO: 05.07.2021 здесь можно что то проверять / тестировать / подгружать настройки дял слайдеров
		viewState.toCreateGoalFromTemplates()
	}

}
