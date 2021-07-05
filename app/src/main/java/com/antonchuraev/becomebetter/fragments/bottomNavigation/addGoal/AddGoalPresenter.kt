package com.antonchuraev.becomebetter.fragments.bottomNavigation.addGoal

import com.antonchuraev.becomebetter.base.BasePresenter
import moxy.InjectViewState

@InjectViewState
class AddGoalPresenter: BasePresenter<AddGoalView>() {

	fun createYourGoalPressed(){
		// TODO: 05.07.2021 здесь можно что то проверять / тестировать / подгружать настройки дял слайдеров

		viewState.showCreateYourGoal()
	}

}
