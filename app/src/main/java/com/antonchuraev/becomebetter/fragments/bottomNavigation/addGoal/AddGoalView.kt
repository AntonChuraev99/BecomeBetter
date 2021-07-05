package com.antonchuraev.becomebetter.fragments.bottomNavigation.addGoal

import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface AddGoalView: MvpView {

	@StateStrategyType(OneExecutionStateStrategy::class )
	fun showCreateYourGoal()

}