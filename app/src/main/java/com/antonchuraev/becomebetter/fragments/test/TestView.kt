package com.antonchuraev.becomebetter.fragments.test

import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface TestView: MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun openTest()

}