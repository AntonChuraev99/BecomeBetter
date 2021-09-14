package com.antonchuraev.becomebetter.base.cicerone

import com.antonchuraev.becomebetter.R
import ru.likebz.toolbox.cicerone.AnimatedScreen
import ru.likebz.toolbox.cicerone.CommonRouter
import ru.likebz.toolbox.cicerone.NavigationInterface
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppScreen
import javax.inject.Inject

class AnimatedNavigation(val baseRouter:CommonRouter) : NavigationInterface , Router() {


    override fun insertScreen(screen: SupportAppScreen) {
        baseRouter.replaceScreen(screen)
    }

    override fun backTo(screen: SupportAppScreen) {
        baseRouter.backTo(screen)
    }

    override fun openNeighborScreen(screen: SupportAppScreen) {
        baseRouter.navigateToWithAdd(screen)
    }

    override fun openFromBottomScreen(screen: SupportAppScreen) {
        baseRouter.navigateToWithAdd(screen)
    }

    override fun openFadeScreen(screen: SupportAppScreen) {
        baseRouter.navigateToWithAdd(screen)
    }

    override fun navigateTo(screen: SupportAppScreen) {
        baseRouter.navigateTo(screen)
    }

    override fun newRootScreen(screen: SupportAppScreen) {
        baseRouter.newRootScreen(screen)
    }


    override fun exit() {
        baseRouter.exit()
    }

    fun onBackPressed() {
        baseRouter.exit()
    }



}