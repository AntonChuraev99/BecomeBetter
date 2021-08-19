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
        baseRouter.navigateToWithAdd(getNeighborAnimatedScreen(screen))
    }

    override fun openFromBottomScreen(screen: SupportAppScreen) {
        baseRouter.navigateToWithAdd(getFromBottomAnimatedScreen(screen))
    }

    override fun openFadeScreen(screen: SupportAppScreen) {
        baseRouter.navigateToWithAdd(getFadeAnimatedScreen(screen))
    }

    override fun navigateTo(screen: SupportAppScreen) {
        baseRouter.navigateTo(getNeighborAnimatedScreen(screen))
    }

    override fun newRootScreen(screen: SupportAppScreen) {
        baseRouter.newRootScreen(getNeighborAnimatedScreen(screen))
    }


    override fun exit() {
        baseRouter.exit()
    }

    fun onBackPressed() {
        baseRouter.exit()
    }

    private fun getNeighborAnimatedScreen(screen: SupportAppScreen) = AnimatedScreen(
        screen,
        R.anim.fragment_from_end_to_start,
        R.anim.fragment_from_start_to_left,
        R.anim.fragment_from_left_to_start,
        R.anim.fragment_from_start_to_end
    )

    private fun getFromBottomAnimatedScreen(screen: SupportAppScreen) = AnimatedScreen(
        screen,
        R.anim.from_bottom_to_top,
        R.anim.from_top_to_bottom,
        R.anim.from_bottom_to_top,
        R.anim.from_top_to_bottom
    )

    private fun getFadeAnimatedScreen(screen: SupportAppScreen) = AnimatedScreen(
        screen,
        R.anim.fade_in,
        R.anim.fragment_def,
        R.anim.fragment_def,
        R.anim.fade_out
    )


}