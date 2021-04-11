package com.antonchuraev.becomebetter.base

import moxy.MvpView
import org.koin.core.inject
import ru.terrakok.cicerone.Router

class AppPresenter: BasePresenter<MvpView>() {

    private val router: Router by inject()

    fun onAppStartSplash() = router.newRootScreen( Screens.Flow.SplashFlow() )

    fun onAppStartBottomNavigation() = router.newRootScreen( Screens.Screen.BottomNavigation() )

}