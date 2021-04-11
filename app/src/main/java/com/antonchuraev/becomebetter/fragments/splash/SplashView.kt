package com.antonchuraev.becomebetter.fragments.splash

import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface SplashView: MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun openMainMenu()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun openTestFragment()

}