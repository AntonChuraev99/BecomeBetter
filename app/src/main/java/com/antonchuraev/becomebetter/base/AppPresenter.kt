package com.antonchuraev.becomebetter.base

import com.antonchuraev.becomebetter.base.cicerone.Screens
import moxy.MvpView
import org.koin.core.inject
import ru.likebz.toolbox.cicerone.CommonRouter
import ru.terrakok.cicerone.Router

class AppPresenter: BasePresenter<MvpView>() {

    private val router: CommonRouter by inject()

    fun onAppStartOpenScreen() {
        // TODO: 07.07.2021 можно сделать превь на 1 экране
        //router.newRootScreen( Screens.Flow.SplashFlow() )
        router.newRootScreen( Screens.Screen.BottomNavigation() )
    }



}